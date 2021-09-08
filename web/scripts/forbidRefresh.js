/**
 * ½ûÖ¹Ë¢ĞÂÍøÒ³£¬°üº¬ÓÒ¼üºÍF5
 */
document.onkeydown = function() {
	if(event.keyCode == 116) {	// F5
          event.keyCode = 0;
          event.returnValue = false;
	}
}

document.oncontextmenu = function() {	// ÓÒ¼ü²Ëµ¥
	event.returnValue = false;
}