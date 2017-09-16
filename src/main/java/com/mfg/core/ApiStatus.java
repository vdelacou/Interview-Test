package com.mfg.core;

/**
 * ^_^
 * Created by gaojiawei on 2016/9/30.
 */
public enum ApiStatus {
    STATUS_200(200, "请求已成功"),
    STATUS_201(201, "请求已经被实现,新的资源已创建"),
    STATUS_202(202, "服务器已接受请求,但尚未处理"),
    STATUS_204(204, "服务器成功处理了请求,但没有返回任何实体内容"),
    STATUS_400(400, "请求参数有误,当前请求无法被服务器理解"),
    STATUS_401(401, "当前请求需要用户验证,或验证失败"),
    STATUS_403(403, "无访问权限,拒绝执行请求"),
    STATUS_404(404, "请求资源未被在服务器上发现"),
    STATUS_405(405, "请求行中指定的请求方法不能被用于请求相应的资源"),
    STATUS_408(408, "请求超时"),
    STATUS_409(409, "由于和被请求的资源的当前状态之间存在冲突,请求无法完成"),
    STATUS_410(410, "被请求的资源在服务器上已经不再可用,而且没有任何已知的转发地址"),
    STATUS_413(413, "请求的实体数据大小超过了服务器能够处理的范围"),
    STATUS_500(500, "服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理"),
    STATUS_501(501, "服务器不支持当前请求所需要的某个功能"),
    STATUS_503(503, "由于临时的服务器维护或者过载,服务器当前无法处理请求");

    private int code;
    private String message;

    private ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
