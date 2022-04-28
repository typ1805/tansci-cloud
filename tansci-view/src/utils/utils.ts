/**
 * 判断是否是移动端
 * @returns 
 */
export const isMobile = () => {
    if (navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)) {
        return true;
    } else {
        return false;
    }
}

/**
 * 时间格式化
 * @param {*} date 
 * @returns 
 */
export function dateTimeFormat(date:any) {
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
    return Y + M + D + h + m + s;
}

/**
 * 时间格式化
 * @param {*} date 
 * @returns 
 */
export function dateFormat(date:any) {
    if (!date) {
        return null;
    }
    date = new Date(date);
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
    return Y + M + D;
}

/**
 * 显示当前时间（年月日时分秒）
 * @param {*} timeStamp 
 * @returns 
 */
export function timeFormate(timeStamp:any) {
    var year = new Date(timeStamp).getFullYear();
    var month = new Date(timeStamp).getMonth() + 1 < 10 ? "0" + (new Date(timeStamp).getMonth() + 1) : new Date(timeStamp).getMonth() + 1;
    var date = new Date(timeStamp).getDate() < 10 ? "0" + new Date(timeStamp).getDate() : new Date(timeStamp).getDate();
    var hh = new Date(timeStamp).getHours() < 10 ? "0" + new Date(timeStamp).getHours() : new Date(timeStamp).getHours();
    var mm = new Date(timeStamp).getMinutes() < 10 ? "0" + new Date(timeStamp).getMinutes() : new Date(timeStamp).getMinutes();
    var ss = new Date(timeStamp).getSeconds() < 10 ? "0" + new Date(timeStamp).getSeconds() : new Date(timeStamp).getSeconds();
    return year + "年" + month + "月" + date + "日" + hh + "时" + mm + '分' + ss + '秒';
}

/**
 * 判断：早上好,上午好,下午好,傍晚好,晚上好
 * @param {*} timeStamp 
 * @returns 
 */
export function timeAddress() {
    var now = new Date();
    var hour = now.getHours();
    var address = "";
    if (hour < 6) {
        address = "凌晨好!";
    } else if (hour < 9) {
        address = "早上好！";
    } else if (hour < 12) {
        address = "上午好！";
    } else if (hour < 14) {
        address = "中午好！";
    } else if (hour < 17) {
        address = "下午好！";
    } else if (hour < 19) {
        address = "傍晚好！";
    } else if (hour < 22) {
        address = "晚上好！";
    } else {
        address = "夜里好！";
    }
    return address;
}

/**
 * 数字格式化为千分位
 * @param {*} num 
 * @returns 
 */
export function numberDormat(num:Number) {
    return (num + '').replace(/(\d{1,3})(?=(\d{3})+(?:$|\.))/g, '$1,');
}