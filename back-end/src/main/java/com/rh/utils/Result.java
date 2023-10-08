package com.rh.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created With IntelliJ IDEA.
 *
 * @Package org.example.vo
 * @Project shop
 * @ClassName R
 * @Description TODO
 * @Author 29496
 * @Date 2023/2/21 10:27 Tue
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /*
    统一响应类
     */
    private Integer code;
    private String msg;
    private Object data;
    /*
    成功
     */
    public static Result ok(){
        return new Result(CodeEnum.SUCCESS.CODE, CodeEnum.SUCCESS.MSG,null);
    }
    public static Result ok(String msg){
        return new Result(CodeEnum.SUCCESS.CODE,msg,null);
    }
    public static Result ok(Integer code, String msg, Object data){
        return new Result(code,msg,data);
    }
    public static Result ok(Object data){
        return new Result(CodeEnum.SUCCESS.CODE, CodeEnum.SUCCESS.MSG,data);
    }
    /*
    错误
     */
    public static Result error(){
        return new Result(CodeEnum.ERROR.CODE, CodeEnum.ERROR.MSG, null);
    }
    public static Result error(String msg){
        return new Result(CodeEnum.ERROR.CODE,msg,null);
    }
    public static Result error(Integer code, String msg, Object data){
        return new Result(code,msg,data);
    }
    public static Result error(Object data){
        return new Result(CodeEnum.ERROR.CODE, CodeEnum.ERROR.MSG,data);
    }


    //状态码枚举类
    public enum CodeEnum {
        /*
        状态码维护
         */
        SUCCESS(200,"成功"),
        ERROR(500,"错误"),
        POWER(300,"TOKEN验证失败");
        public final Integer CODE;
        public final String MSG;
        CodeEnum(Integer code,String msg){
            this.CODE = code;
            this.MSG=msg;
        }
    }

}
