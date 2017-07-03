package com.allen.web.controller.datachange;

import com.allen.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Allen on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/addDataChangeForEditReson")
public class AddDataChangeForEditResonController extends BaseController {

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open()throws Exception{
        return "datachange/addReson";
    }
}
