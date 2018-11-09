/**
 * 将时间戳长整数格式化为日期字符串
 * @param date
 * @param useHMS 是否需要时分秒
 * @returns
 */
function dateToString(date, useHMS){
	var d = new Date(date);
	var str = d.getFullYear() + "-" + (d.getMonth()+1<10?"0"+(d.getMonth()+1):(d.getMonth()+1)) + "-" + (d.getDate()<10?"0"+d.getDate():d.getDate());
	if(useHMS){
		str += " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
	}
	return str;
}