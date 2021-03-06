package com.allen.util;

import com.allen.base.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by Allen on 2015/6/22.
 */
public class UpLoadFileUtil {

    /**
     * 上传图片
     * @param request
     * @param imgType
     * @param imgSize
     * @param imgMaxCount
     * @param imgPath
     * @return
     * @throws Exception
     */
    public static String uploadImg(HttpServletRequest request,List<MultipartFile> fileList,String imgType, int imgSize, int imgMaxCount, String imgPath, String saveFileName)throws Exception{
        String imgUrl = "";
        //创建一个通用的多部分解析器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(commonsMultipartResolver.isMultipart(request)) {
            //取得request中的所有文件名
            if(null != fileList && 0 < fileList.size()) {
                //判断上传文件数量
                if (fileList.size() > imgMaxCount) {
                    throw new BusinessException("最多只能上传" + imgMaxCount + "张图片！");
                }
                for (int i=0; i<fileList.size(); i++) {
                    MultipartFile file = fileList.get(i);
                    //取得上传文件
                    if (file != null) {
                        //取得当前上传文件的文件名称
                        String fileName = file.getOriginalFilename();
                        //取得当前上传文件大小
                        long fileSize = file.getSize();
                        //如果名称不为"",说明该文件存在，否则说明该文件不存在
                        if (!StringUtil.isEmpty(fileName.trim()) && 0 < fileSize) {
                            //判断上传文件类型
                            String fileExtention = StringUtil.substringAfterLast(fileName, ".").toLowerCase();
                            if (!fileExtention.matches(imgType)) {
                                throw new BusinessException("图片的类型只能是：" + imgType + "！");
                            }
                            //判断上传文件大小
                            if (fileSize / 1024 > imgSize) {
                                throw new BusinessException("图片最大不能超过" + imgSize + "kb");
                            }
                            //重命名上传后的文件名
                            fileName = StringUtil.isEmpty(saveFileName) ? UUID.randomUUID().toString() : saveFileName + ".png";
                            //判断路径是否存在，不存在就创建
                            //定义上传路径
                            String savePath = imgPath + fileName;
                            //服务器tomcat路径+保存路径
                            File localFile = new File(request.getRealPath("") + imgPath);
                            localFile.mkdirs();
                            localFile = new File(request.getRealPath("") + savePath);
                            file.transferTo(localFile);
                            imgUrl += savePath + ",";
                        }
                    }
                }
            }
        }
        return  imgUrl.substring(0, imgUrl.length() > 0 ? imgUrl.length()-1 : 0);
    }

    /**
     * 删除文件
     * @param path
     * @throws Exception
     */
    public static void delFile(HttpServletRequest request, String path)throws Exception{
        File file = new File(request.getRealPath("") + path);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public static boolean delDir(String path)throws Exception{
        File dir = new File(path);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                File childrenFile = new File(dir, children[i]);
                boolean success = delDir(childrenFile.getPath());
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(HttpServletRequest request, String oldPath, String newPath, String fileName) {
        try {
            int bytesum = 0;
            int byteread = 0;
            oldPath = request.getRealPath("")+oldPath;
            newPath = request.getRealPath("")+newPath;
            new File(newPath).mkdirs();
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath+"/"+fileName);
                byte[] buffer = new byte[2048];
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 剪切文件
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void custFile(HttpServletRequest request, String oldPath, String newPath, String fileName) {
        try {
            copyFile(request, oldPath, newPath, fileName);
            delFile(request, oldPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
