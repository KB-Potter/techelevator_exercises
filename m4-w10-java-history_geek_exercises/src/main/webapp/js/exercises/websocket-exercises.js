$(document).ready(function () {
    
	var socket = new SockJS("ws-connect");
	var connection = Stomp.over(socket);
	
	var errorCallback = function(error) {
		console.log(error);
	}
	
	var successCallback = function(frame) {
		console.log("Connected: " + frame);
		connection.subscribe('/topic/members', function(members) {
			refreshMemberList(members);
		});
		connection.subscribe('/topic/chat', function(message) {
			addToChatWindow(message);
		});
	}
	
	connection.connect({}, successCallback, errorCallback);
	
	$('form#chatForm').on('submit', function(e) {
		var chat = $("textarea[name='message']").val();
		connection.send("/app/chat", {}, JSON.stringify({'message' : chat}));
		$("textarea[name='message']").val("");
	});
	
});

function refreshMemberList(message) {
	var members = JSON.parse(message.body);
	$("#members ul").empty();
	for (var i = 0; i < members.length; i++) {
		var listItem = $("<li>").text(members[i]);
		$("#members ul").append(listItem);
	}
}

function addToChatWindow(message) {
	var chat = JSON.parse(message.body);
	
	var time = new Date(
	           chat.sentDate.year,
	           chat.sentDate.monthValue,
	           chat.sentDate.dayOfMonth,
	           chat.sentDate.hour,
	           chat.sentDate.minute,
	           chat.sentDate.second
	   ).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

	
	var p = $("<p>").addClass("message");
	var userspan = $("<span>").addClass("username").text(chat.userName + " ");
	var timespan = $("<span>").addClass("time").text(time);
	var messagespan = $("<span>").text(chat.message);
	p.append(userspan).append(timespan).append($("<br>")).append(messagespan);
	$("#history").append(p);
}