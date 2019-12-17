/**
 * Utilities for the inbox consultation view.
 * 
 * @author chiridl
 */
function _showSorterImage() {
	var _field = $("sortField").value;
	var _direction = ($("sortDirection").value == "asc") ? "asc" : "desc";
	
	if ($("img_sortby" + _field)) {
		$("img_sortby" + _field).src = "./images/icons/sort_" + _direction + ".png";
		$("img_sortby" + _field).style.visibility = "visible";
		$("img_sortby" + _field).style.display = "inline";
	}
}

window.addEvent("domready", function() {
	_showSorterImage();
	
	$("filter-btn").addEvent("click", function(){
		if ($("id").value == "" && $("subject").value == "" && $("from").value == "" && $("attachments").value == "") {
			alert("Please define some filtering criteria first!");
			return false;
		}
		
		$("realAction").value = "filter";
		alert(document.getElementById("inboxForm"));
		document.getElementById("inboxForm").submit();
	});		    

	$("delete-filter-btn").addEvent("click", function(){
		$("realAction").value = "clear";
		alert(document.getElementById("inboxForm"));
		document.getElementById("inboxForm").submit();
	});
	
	Array.each(["id", "subject", "from", "sent", "received", "attachments"], function(_field, _idx){
		$("sortby" + _field).addEvent("click", function(){
			$("realAction").value = "sort";
			$("sortField").value = _field;
			alert(_field);
			$("sortDirection").value = ($("sortDirection").value == "asc") ? "desc" : "asc";
			alert(document.getElementById("inboxForm"));
			document.getElementById("inboxForm").submit();
			return false;
		});
	});
});		
