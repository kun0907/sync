package com.dkd.emms.systemManage.bo;

public class DeliveryVehicle {
    /**
     * 发货运输工具ID（系统生成）
     */
    private String deVehicleId;

    /**
     * 发货单ID（带入）
     */
    private String deliveryId;

    /**
     * 发货单号（带入）
     */
    private String deliveryNo;

    /**
     * 运输方式（陆运、空运、海运、铁运）
     */
    private String transportMode;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     *航班号
     * @mbggenerated Mon Feb 27 18:53:11 CST 2017
     */
    private String flatNumber;

    /**
     * 船名号
     */
    private String shipNumber;

    /**
     *起运地
     * @mbggenerated Mon Feb 27 18:53:11 CST 2017
     */
    private String startPlace;

    /**
     *目的地
     * @mbggenerated Mon Feb 27 18:53:11 CST 2017
     */
    private String destination;

    /**
     * 负责人
     * @mbggenerated Mon Feb 27 18:53:11 CST 2017
     */
    private String chargePersonName;

    /**
     *负责人电话
     * @mbggenerated Mon Feb 27 18:53:11 CST 2017
     */
    private String chargePersonPhone;

    /**
     *状态（新建、装车完成、发运）
     * @mbggenerated Mon Feb 27 18:53:11 CST 2017
     */
    private String deVehicleState;
    /**
     * 包装编号
     */
    private String packageNo;

    public String getDeVehicleId() {
        return deVehicleId;
    }

    public void setDeVehicleId(String deVehicleId) {
        this.deVehicleId = deVehicleId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getChargePersonName() {
        return chargePersonName;
    }

    public void setChargePersonName(String chargePersonName) {
        this.chargePersonName = chargePersonName;
    }

    public String getChargePersonPhone() {
        return chargePersonPhone;
    }

    public void setChargePersonPhone(String chargePersonPhone) {
        this.chargePersonPhone = chargePersonPhone;
    }

    public String getDeVehicleState() {
        return deVehicleState;
    }

    public void setDeVehicleState(String deVehicleState) {
        this.deVehicleState = deVehicleState;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }
}