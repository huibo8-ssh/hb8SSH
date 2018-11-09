/**
 * 自定义扩展easyui的表单验证规则
 */
$.extend($.fn.validatebox.defaults.rules, {    
	chineseName: {    
        validator: function(value, param){
        	var reg = /^[\u4e00-\u9fa5]+$/;
        	if(value.length < param[0] || value.length > param[1]){
        		return false;
        	}
            return reg.test(value);    
        },    
        message: '请输入{0}到{1}位中文姓名.'  //{0}表示获取param数组中的第1个参数值 {1}表示获取param数组中的第2个参数值
    },
    age: {    
        validator: function(value, param){
        	var reg = /^\d{2}$/;
            return reg.test(value) && value >= 18 && value <= 60;    
        },    
        message: '年龄只能是18到60之间的整数.'   
    },
    mobile: {    
        validator: function(value, param){
        	var reg = /^1(3|4|5|7|8|9)\d{9}$/;
            return reg.test(value);    
        },    
        message: '请输入手机号.'   
    },
    email: {    
        validator: function(value, param){
        	var reg = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
            return reg.test(value);    
        },    
        message: '请输入邮箱地址.'   
    }
});  
