package com.ancaiyun.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberExtraData implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    private String id;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 注册资金(万元)
     */
    private BigDecimal companyRegisteredCapital;
    /**
     * 员工人数 0:50人以下1:50-150人2:151-500人3:501-1000人4:1000人以上
     */
    private Integer companyEmployeeNumber;
    /**
     * 公司电话
     */
    private String companyTelephone;
    /**
     * 公司所在地
     */
    private String companyProvinceCityCountry;
    /**
     * 单位性质1:国有企业 2:集体所有制3:私营企业 4:股份制企业  5:有限责任公  6:股份有限公司 7:有限合伙企业 8:联营企业  9外商投资企 10个人独资企业11港、澳、台商投资企业  12股份合作企业    13:其他有限责任公司 14:个体工商户
     */
    private String companyEnterpriseNature;
    /**
     * 纳税人类型1一般纳税人2小规模纳税人
     */
    private String companyTaxpayerType;
    /**
     * 每年营业额1：100万以下2：100万以上3：1000万-5000万4：5000万-1亿5：1亿-10亿6：10亿以上
     */
    private String companyAnnualTurnover;
    /**
     * 纳税人税率
     */
    private String companyTaxRate;
    /**
     * 公司详细地址
     */
    private String companyAddress;
    /**
     * 经营范围1生产制造2贸易批发3商业服务4其他组织
     */
    private String companyBusinessScope;
    /**
     * 法人姓名
     */
    private String companyLegalPersonName;
    /**
     * 企业网址
     */
    private String companyWebsite;
    /**
     * 法人证件类型0：身份证1：护照2其他
     */
    private String companyLegalPersonCertificateType;
    /**
     * 法人证件号
     */
    private String companyLegalPersonCertificateNumber;
    /**
     * 法人电话
     */
    private String companyLegalPersonCertificateTelephone;
    /**
     * 营业执照三证合一
     */
    private String companyBusinessLicense;
    /**
     * 统一社会信用代码
     */
    private String companyUnifiedSocialCreditCode;
    /**
     * 发证日期
     */
    private Date companyBusinessLicenseIssueDate;
    /**
     * 注册成立日期
     */
    private Date companyBusinessLicenseRegisterDate;
    /**
     * 有效日期
     */
    private Date companyBusinessLicenseValidatyDate;
    /**
     * 营业执照公司所在地
     */
    private String companyBusinessLicenseAddress;
    /**
     * 营业执照证件电子版
     */
    private String companyBusinessLicenseElectronicVersion;
    /**
     * 企业授权书证件电子版
     */
    private String companyEnterpriseAuthorizationElectronicVersion;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 删除标志位 0:正常 1：删除 2：待删除
     */
    private String delFlag;
    /**
     * 企业简介
     */
    private String company_info_detail;
    /**
     * 公司logo
     */
    private String companyLogoId;
    /**
     * 联系人职务
     */
    private String contactPosition;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人联系方式
     */
    private String contactPhone;
    /**
     * 注册地址
     */
    private String registeredAddress;
    /**
     * 基础利率
     */
    private BigDecimal basicRate;
    /**
     * 上浮利率
     */
    private BigDecimal floatRate;
    /**
     * 最终利率
     */
    private BigDecimal finalRate;
    /**
     * 企业简介
     */
    private String companyInfoDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public BigDecimal getCompanyRegisteredCapital() {
        return companyRegisteredCapital;
    }

    public void setCompanyRegisteredCapital(BigDecimal companyRegisteredCapital) {
        this.companyRegisteredCapital = companyRegisteredCapital;
    }

    public Integer getCompanyEmployeeNumber() {
        return companyEmployeeNumber;
    }

    public void setCompanyEmployeeNumber(Integer companyEmployeeNumber) {
        this.companyEmployeeNumber = companyEmployeeNumber;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone == null ? null : companyTelephone.trim();
    }

    public String getCompanyProvinceCityCountry() {
        return companyProvinceCityCountry;
    }

    public void setCompanyProvinceCityCountry(String companyProvinceCityCountry) {
        this.companyProvinceCityCountry = companyProvinceCityCountry == null ? null : companyProvinceCityCountry.trim();
    }

    public String getCompanyEnterpriseNature() {
        return companyEnterpriseNature;
    }

    public void setCompanyEnterpriseNature(String companyEnterpriseNature) {
        this.companyEnterpriseNature = companyEnterpriseNature == null ? null : companyEnterpriseNature.trim();
    }

    public String getCompanyTaxpayerType() {
        return companyTaxpayerType;
    }

    public void setCompanyTaxpayerType(String companyTaxpayerType) {
        this.companyTaxpayerType = companyTaxpayerType == null ? null : companyTaxpayerType.trim();
    }

    public String getCompanyAnnualTurnover() {
        return companyAnnualTurnover;
    }

    public void setCompanyAnnualTurnover(String companyAnnualTurnover) {
        this.companyAnnualTurnover = companyAnnualTurnover == null ? null : companyAnnualTurnover.trim();
    }

    public String getCompanyTaxRate() {
        return companyTaxRate;
    }

    public void setCompanyTaxRate(String companyTaxRate) {
        this.companyTaxRate = companyTaxRate == null ? null : companyTaxRate.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyBusinessScope() {
        return companyBusinessScope;
    }

    public void setCompanyBusinessScope(String companyBusinessScope) {
        this.companyBusinessScope = companyBusinessScope == null ? null : companyBusinessScope.trim();
    }

    public String getCompanyLegalPersonName() {
        return companyLegalPersonName;
    }

    public void setCompanyLegalPersonName(String companyLegalPersonName) {
        this.companyLegalPersonName = companyLegalPersonName == null ? null : companyLegalPersonName.trim();
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite == null ? null : companyWebsite.trim();
    }

    public String getCompanyLegalPersonCertificateType() {
        return companyLegalPersonCertificateType;
    }

    public void setCompanyLegalPersonCertificateType(String companyLegalPersonCertificateType) {
        this.companyLegalPersonCertificateType = companyLegalPersonCertificateType == null ? null : companyLegalPersonCertificateType.trim();
    }

    public String getCompanyLegalPersonCertificateNumber() {
        return companyLegalPersonCertificateNumber;
    }

    public void setCompanyLegalPersonCertificateNumber(String companyLegalPersonCertificateNumber) {
        this.companyLegalPersonCertificateNumber = companyLegalPersonCertificateNumber == null ? null : companyLegalPersonCertificateNumber.trim();
    }

    public String getCompanyLegalPersonCertificateTelephone() {
        return companyLegalPersonCertificateTelephone;
    }

    public void setCompanyLegalPersonCertificateTelephone(String companyLegalPersonCertificateTelephone) {
        this.companyLegalPersonCertificateTelephone = companyLegalPersonCertificateTelephone == null ? null : companyLegalPersonCertificateTelephone.trim();
    }

    public String getCompanyBusinessLicense() {
        return companyBusinessLicense;
    }

    public void setCompanyBusinessLicense(String companyBusinessLicense) {
        this.companyBusinessLicense = companyBusinessLicense == null ? null : companyBusinessLicense.trim();
    }

    public String getCompanyUnifiedSocialCreditCode() {
        return companyUnifiedSocialCreditCode;
    }

    public void setCompanyUnifiedSocialCreditCode(String companyUnifiedSocialCreditCode) {
        this.companyUnifiedSocialCreditCode = companyUnifiedSocialCreditCode == null ? null : companyUnifiedSocialCreditCode.trim();
    }

    public Date getCompanyBusinessLicenseIssueDate() {
        return companyBusinessLicenseIssueDate;
    }

    public void setCompanyBusinessLicenseIssueDate(Date companyBusinessLicenseIssueDate) {
        this.companyBusinessLicenseIssueDate = companyBusinessLicenseIssueDate;
    }

    public Date getCompanyBusinessLicenseRegisterDate() {
        return companyBusinessLicenseRegisterDate;
    }

    public void setCompanyBusinessLicenseRegisterDate(Date companyBusinessLicenseRegisterDate) {
        this.companyBusinessLicenseRegisterDate = companyBusinessLicenseRegisterDate;
    }

    public Date getCompanyBusinessLicenseValidatyDate() {
        return companyBusinessLicenseValidatyDate;
    }

    public void setCompanyBusinessLicenseValidatyDate(Date companyBusinessLicenseValidatyDate) {
        this.companyBusinessLicenseValidatyDate = companyBusinessLicenseValidatyDate;
    }

    public String getCompanyBusinessLicenseAddress() {
        return companyBusinessLicenseAddress;
    }

    public void setCompanyBusinessLicenseAddress(String companyBusinessLicenseAddress) {
        this.companyBusinessLicenseAddress = companyBusinessLicenseAddress == null ? null : companyBusinessLicenseAddress.trim();
    }

    public String getCompanyBusinessLicenseElectronicVersion() {
        return companyBusinessLicenseElectronicVersion;
    }

    public void setCompanyBusinessLicenseElectronicVersion(String companyBusinessLicenseElectronicVersion) {
        this.companyBusinessLicenseElectronicVersion = companyBusinessLicenseElectronicVersion == null ? null : companyBusinessLicenseElectronicVersion.trim();
    }

    public String getCompanyEnterpriseAuthorizationElectronicVersion() {
        return companyEnterpriseAuthorizationElectronicVersion;
    }

    public void setCompanyEnterpriseAuthorizationElectronicVersion(String companyEnterpriseAuthorizationElectronicVersion) {
        this.companyEnterpriseAuthorizationElectronicVersion = companyEnterpriseAuthorizationElectronicVersion == null ? null : companyEnterpriseAuthorizationElectronicVersion.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getCompanyLogoId() {
        return companyLogoId;
    }

    public void setCompanyLogoId(String companyLogoId) {
        this.companyLogoId = companyLogoId == null ? null : companyLogoId.trim();
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition == null ? null : contactPosition.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress == null ? null : registeredAddress.trim();
    }

    public BigDecimal getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(BigDecimal basicRate) {
        this.basicRate = basicRate;
    }

    public BigDecimal getFloatRate() {
        return floatRate;
    }

    public void setFloatRate(BigDecimal floatRate) {
        this.floatRate = floatRate;
    }

    public BigDecimal getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(BigDecimal finalRate) {
        this.finalRate = finalRate;
    }

    public String getCompanyInfoDetail() {
        return companyInfoDetail;
    }

    public void setCompanyInfoDetail(String companyInfoDetail) {
        this.companyInfoDetail = companyInfoDetail == null ? null : companyInfoDetail.trim();
    }
    public String getCompany_info_detail() {
        return company_info_detail;
    }

    public void setCompany_info_detail(String company_info_detail) {
        this.company_info_detail = company_info_detail;
    }
}