package com.ccm.mzjhapp.api.controller;

import com.ccm.base.tips.ErrorTip;
import com.ccm.base.tips.Tip;
import com.ccm.base.utils.RegexUtil;
import com.ccm.base.utils.RespEnum;
import com.ccm.mzjhapp.base.util.DocUtil;
import com.ccm.mzjhapp.base.vo.DtmjReportInfo;
import com.ccm.mzjhapp.ws.shmj.lisris.ILisRisReportWsServiceSH;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/16 17:25
 * @Description:
 */
@Api(tags = "检查报告api")
@RestController
@RequestMapping("/api")
public class ExamReportController {

    @ApiOperation(value = "获取检查报告列表", /*tags = "report",*/ response = Tip.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "患者唯一识别号", name = "patientID", dataType = "String", required = true),
            @ApiImplicitParam(value = "查询开始时间,格式 yyyy-mm-dd hh24:mi:ss", name = "startTime", dataType = "String"),
            @ApiImplicitParam(value = "查询结尾时间,格式 yyyy-mm-dd hh24:mi:ss", name = "endTime", dataType = "String")})
    @PostMapping("/getExamReports")
    public Object getExamReports(@RequestParam String patientID,
                                @RequestParam(required = false) String startTime,
                                @RequestParam(required = false) String endTime) {

        if (!StringUtils.isEmpty(startTime) && !RegexUtil.isDateTime(startTime)) {
            return new ErrorTip(RespEnum.ERR_PARAM.getCode(),RespEnum.ERR_PARAM.getMessage());
        }
        if (!StringUtils.isEmpty(endTime) && !RegexUtil.isDateTime(endTime)) {
            return new ErrorTip(RespEnum.ERR_PARAM.getCode(),RespEnum.ERR_PARAM.getMessage());
        }

        ILisRisReportWsServiceSH ws = new ILisRisReportWsServiceSH(ILisRisReportWsServiceSH.WSDL_LOCATION);
        try {
            String param = ControllerSupport.buildReportParam(patientID, startTime, endTime);
            String respParam = ws.getILisRisReportWsServiceHttpPort().mjCloudGetReportInfo(param);
            List<DtmjReportInfo> list = DocUtil.getAnalysisCreateReport(respParam, patientID);

            /*if (ListUtil.isNotEmpty(list)){
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(0).getZchar1().equals("0")) {

                    }else{
                        list.get(0).getZchar2();
                    }
                }
            }*/
            return list;
        } catch (Exception e) {
            return new ErrorTip(100,e.getMessage());
        }
    }
}
