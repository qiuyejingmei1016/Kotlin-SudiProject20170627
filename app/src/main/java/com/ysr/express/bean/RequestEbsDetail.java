package com.ysr.express.bean;

import java.util.List;

/**及时查询
 * Created by ysr on 2017/7/14 15:52.
 * 邮箱 ysr200808@163.com
 */

public class RequestEbsDetail {

    /**
     * EBusinessID : 1293600
     * ShipperCode : YD
     * Success : true
     * LogisticCode : 3999043346251
     * State : 3
     * Traces : [{"AcceptTime":"2017-06-04 22:15:12","AcceptStation":"到达：广东广州增城市新塘公司牛仔王便民寄存点 已收件"},{"AcceptTime":"2017-06-05 02:12:53","AcceptStation":"到达：广东广州增城市新塘公司 发往：广东深圳公司"},{"AcceptTime":"2017-06-05 03:38:36","AcceptStation":"到达：广东广州分拨中心黄埔分拨点"},{"AcceptTime":"2017-06-05 03:38:58","AcceptStation":"到达：广东广州分拨中心黄埔分拨点 发往：广东广州分拨中心"},{"AcceptTime":"2017-06-05 08:12:48","AcceptStation":"到达：广东广州分拨中心 上级站点：广东广州分拨中心黄埔分拨点"},{"AcceptTime":"2017-06-05 08:14:47","AcceptStation":"到达：广东广州分拨中心 发往：广东深圳公司"},{"AcceptTime":"2017-06-05 12:11:06","AcceptStation":"到达：广东深圳公司 上级站点：广东广州分拨中心"},{"AcceptTime":"2017-06-05 12:28:33","AcceptStation":"到达：广东深圳公司 发往：广东深圳公司福田区海滨分拨分部"},{"AcceptTime":"2017-06-05 14:05:38","AcceptStation":"到达：广东深圳公司福田区海滨分拨分部 发往：广东深圳公司福田区岗厦东西村分部"},{"AcceptTime":"2017-06-05 21:10:06","AcceptStation":"到达：广东深圳公司福田区岗厦东西村分部 指定：颜烈彬(13822143644) 派送"},{"AcceptTime":"2017-06-05 21:18:20","AcceptStation":"到达：广东深圳公司福田区岗厦东西村分部 由 已签收 签收"}]
     */

    private String EBusinessID;
    private String ShipperCode;
    private boolean Success;
    private String LogisticCode;
    private String State;
    private List<TracesBean> Traces;

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public List<TracesBean> getTraces() {
        return Traces;
    }

    public void setTraces(List<TracesBean> Traces) {
        this.Traces = Traces;
    }

    public static class TracesBean {
        /**
         * AcceptTime : 2017-06-04 22:15:12
         * AcceptStation : 到达：广东广州增城市新塘公司牛仔王便民寄存点 已收件
         */

        private String AcceptTime;
        private String AcceptStation;

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }
    }
}
