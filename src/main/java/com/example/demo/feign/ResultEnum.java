package com.example.demo.feign;

/**
 * @author yu.zhang
 * created on 2022-02-16
 */
public enum ResultEnum {
    /**
     * 结果枚举
     */
    SUCCESS(0, "ok"),
    BIZ_ERROR(101, "业务异常"),
    FORM_REPEAT_SUBMIT(102, "表单重复提交"),
    UNKNOWN_ERROR(500, "系统遇到一点问题，已经反馈给研发人员"),
    TOKEN_EXPIRED(100000, "登陆失效"),
    
    PARAM_MISS(100001, "参数不全"),
    PARAM_ERROR(100002, "参数错误"),

    UNKNOWN_USER(200000, "用户不存在"),
    PWD_ERROR(200001, "密码错误"),
    PWD_SAME(200002,"新旧密码不能相同"),
    
    UNKNOWN_TYPE(30000, "未知类型"),
    UPLOAD_FAIL(300001,"文件上传失败"),

    LIMIT_FAIL(300002,"文件大小超过限制"),

    PUSH_OA_FAIL(500001, "推送oa表单失败，系统稍后会自动重试"),
    
    LOGIN_FAILURE(301,"登录失败，用户信息状态不是success"), LOGIN_REEOR(302,"登录失败，未查询到用户信息"),
    HAS_PRODUCT_FAIL(400001, "该活动下有商品，不支持删除"),
    ACTIVITY_HAS_BILL_FAIL(400002, "该活动下有账单，不支持删除"),
    HAS_COUPON_CODE_FAIL(400003, "该商品关联商品code，不支持删除"),
    HAS_BATCH_FAIL(400004, "该商品关联批次，不支持删除"),
    PRODUCT_HAS_BILL_FAIL(400005, "该商品下有账单，不支持删除"),
    CONTRACT_ABOLITIONED_FAIL(400006, "该合同已作废"),
    CONTRACT_ABOLITIONING_FAIL(400007, "该合同作废申请中"),
    PRODUCT_NEED_RERUN_FAIL(400008, "该活动ID下的商品账单已经核账/开票/回款，无法重跑账单"),
    PRODUCT_NEED_RERUN_SUCCESS(400009, "检测到修改商品类型，是否重跑账单"),
    CAN_NOT_DELETE(400010, "不支持删除"),
    ;

    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
