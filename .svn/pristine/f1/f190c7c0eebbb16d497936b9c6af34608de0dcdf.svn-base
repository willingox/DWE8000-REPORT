
//var req = null;

var READY_STATE_UNINITIALIZED = 0;
var READY_STATE_LOADING = 1;
var READY_STATE_LOADED = 2;
var READY_STATE_INTERACTIVE = 3;
var READY_STATE_COMPLETE = 4;


function initXMLHttpRequest() {
	var xRequest = null;
	if (window.XMLHttpRequest) {
		/* all browsers except IE */
		xRequest = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		try {
			xRequest = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e) {
			try {
				/* IE with older system libraries */
				xRequest = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e) {
				xRequest = null;
			}
		}
	}
	return xRequest;
}

function sendXMLHttpRequest(url, params, method, onload, onerror, asyn) {
	var req = initXMLHttpRequest();
	if (req) {
		if (!method) {
			method = "GET";
		}
		if (!asyn) {
			asyn = true;
		}
		//var timer = null;
		req.onreadystatechange = function () {
			if (req.readyState == READY_STATE_COMPLETE) {
				/*if (timer) {
					clearTimeout(timer);
					timer = null;
				}*/
				if (req.status == 200) {
					if (onload) {
						// req.getResponseHeader('Content-Type')
						onload(req.responseText);
					}
				}
				else {
					if (onerror) {
						onerror(req.status, req.statusText);
					}
				}
			}
		};
		req.open(method, url, asyn);
		if (method == "POST") {
			req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		}
		req.setRequestHeader("If-Modified-Since", "0");
		/*timer = setTimeout(function () {
			req.send(params);
		}, 0);*/
		req.send(params);
	}
}
