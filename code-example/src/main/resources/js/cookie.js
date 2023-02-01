define(["jquery", "common/utils/validationUtils"], function ($, validator) {
    var timeUnit = {};
    timeUnit.MINUTE = 60 * 1E3;
    timeUnit.HOUR = timeUnit.MINUTE * 60;
    timeUnit.DAY = timeUnit.HOUR * 24;
    timeUnit.WEEK = timeUnit.DAY * 7;
    timeUnit.MONTH = timeUnit.DAY * 30;
    timeUnit.YEAR = timeUnit.DAY * 365;
    var cookieCheckList = ["searchKeyword"];
    var cookieDomain = {dev: "coupangdev.com", prod: "coupang.com"};
    var cookieUtils = {
        cookie: function (name, value, opts) {
            var expires = "", path, domain, secure, cookieValue, cookies, date, options = $.extend({
                timeUnit: "DAY",
                timeValue: 1,
                domain: document.domain.indexOf("coupang.com") > -1 ? cookieDomain.prod : cookieDomain.dev,
                path: "/"
            }, opts || {});
            if (value === null) {
                value = "";
                options.timeValue = -1
            }
            if (typeof value !== "undefined") {
                if (options.timeValue && (typeof options.timeValue === "number" || options.timeValue.toUTCString)) {
                    if (typeof options.timeValue === "number") {
                        date = new Date;
                        date.setTime(date.getTime() + options.timeValue * timeUnit[options.timeUnit.toUpperCase()])
                    } else date = options.timeValue;
                    expires = "; expires\x3d" + date.toUTCString()
                }
                path =
                    options.path ? "; path\x3d" + options.path : "";
                domain = options.domain ? "; domain\x3d" + options.domain : "";
                secure = options.secure ? "; secure" : "";
                document.cookie = [name, "\x3d", encodeURIComponent(value), expires, path, domain, secure].join("")
            } else {
                cookieValue = null;
                if (document.cookie && document.cookie !== "") {
                    cookies = document.cookie.split(";");
                    for (var i = 0; i < cookies.length; i++) {
                        var cookie = $.trim(cookies[i]);
                        if (cookie.substring(0, name.length + 1) === name + "\x3d") {
                            cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                            break
                        }
                    }
                }
                return cookieValue
            }
        }, erase: function (name) {
            cookieUtils.cookie(name, null)
        }, addCookie: function (name, value, delimiter, opts) {
            var KEYWORD_MAX_BYTE = 300;
            if ($.inArray(name, cookieCheckList) !== -1) if (encodeURIComponent(value).length > KEYWORD_MAX_BYTE) return;
            var MAX_KEYWORD_NUMBER = 9;
            var delimiter = delimiter || "|";
            var cookies = this.getValue(name);
            var newValue = $.trim(value);
            var removeValueIfExists = opts.removeAndUpdateValue;
            if (!cookies) {
                this.setCookie(name, newValue, opts);
                return
            }
            var cookieArr = cookies.split(delimiter);
            var isValueInCookieAlready = cookies && $.inArray(newValue, cookieArr) !== -1;
            if (newValue.length === 0 || !!isValueInCookieAlready) if (!!removeValueIfExists) {
                cookieArr = this.removeCookieValue(name, newValue, delimiter);
                cookies = cookieArr.join(delimiter)
            } else return;
            if (cookieArr.length >= MAX_KEYWORD_NUMBER) {
                cookieArr.splice(0, cookieArr.length - MAX_KEYWORD_NUMBER + 1);
                cookies = cookieArr.join(delimiter)
            }
            if (!!cookies) newValue = cookies + delimiter + newValue;
            this.setCookie(name, newValue, opts)
        }, substractCookie: function (name, value,
                                      delimiter, opts) {
            var delimiter = delimiter || "|";
            var cookieArr = this.removeCookieValue(name, value, delimiter);
            this.setCookie(name, cookieArr.join(delimiter), opts)
        }, removeCookieValue: function (name, value, _delimiter) {
            var delimiter, cookieArr, delPos;
            delimiter = _delimiter || "|";
            cookieArr = this.getValue(name).split(delimiter);
            for (var i = 0; i < cookieArr.length; i++) if (cookieArr[i] === value) {
                delPos = i;
                break
            }
            cookieArr.splice(delPos, 1);
            return cookieArr
        }
    };
    var exportMethod = {
        setCookie: cookieUtils.cookie,
        getValue: cookieUtils.cookie,
        erase: cookieUtils.erase,
        addCookie: cookieUtils.addCookie,
        substractCookie: cookieUtils.substractCookie,
        removeCookieValue: cookieUtils.removeCookieValue,
        exist: function (name) {
            return this.getValue(name) !== null
        }
    };
    return exportMethod
});
