<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- <script src= "https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"> </script>  -->
<title>mySAL - Chat</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.css" rel="stylesheet">
<!-- custom.css includes styling for the chat box -->
<link href="css/custom.css" rel="stylesheet">

<!-- jQuery -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<!-- Firebase -->
<script src="https://www.gstatic.com/firebasejs/3.3.0/firebase.js"></script>

<!-- Firechat -->
<link rel="stylesheet"
	href="https://cdn.firebase.com/libs/firechat/3.0.1/firechat.min.css" />
<script
	src="https://cdn.firebase.com/libs/firechat/3.0.1/firechat.min.js"></script>

</head>

<body id="page-top">


	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- End of Sidebar -->
		
		<!-- Chat Box Component (float on top of everything) -->
		<div class="chatBoxContainer row" id="chatBox">

			<!-- Chat List (left-hand-side) -->
			<div class="col1">
				<ul id="chatList" class="chatList">
					<div class="chatListContainer">
						<!-- Search contact Textarea -->
						<div class="form-inline md-form form-sm mt-0"
							style="padding: 7px; padding-left: 10px;">
							<i class="fas fa-search" aria-hidden="true"></i> <input
								id="searchContactBox"
								class="form-control form-control-sm ml-3 w-75" type="text"
								placeholder="Search contact" aria-label="Search">
						</div>
						<!-- Dynamic List of contacts -->
						<div id="contactList"></div>
						<!--  
						<li class="chatListRow row d-flex flex-row align">
							<div class="chatListProfile">
								<img class="chatListProfilePicture rounded-circle" src=timmy.png>
							</div> <text class="chatListName">Timmy</text>
						</li>
						-->
					</div>


				</ul>
			</div>
			<!-- Message Area (right-hand-side) -->
			<div class="col2">
				<!-- Header: showing current chat recipient name -->
				<div class="chatBoxHeader row" id="chatHead">
					<custom name="chatHeaderRecipient"
						class="chatHeaderRecipient font-weight-bold" id="chatHeaderRecipient">:name:</custom>
					<div class="closeChatBoxButton">
						<i class="fa fa-times" style="z-index: 1"
							onclick="closeChatBox();"></i>
					</div>
				</div>
				<!-- Dynamic Chat Log Box -->
				<ul id="chatLog" class="chatLog list-group">
					<!-- 
					<li class="chatLogRow row d-flex flex-row">
						
						<div class="senderProfile">
							<img class="senderProfilePicture rounded-circle" src=timmy.png>
						</div>
						
						<div class="senderMessage">
							<text>Hello! This is a sample message with a lot of text.
							If anyone knows how to have the profile picture align with the
							top of this paragraph please change the code.</text>
						</div>
						
					</li>
					-->
				</ul>
				<!-- Chat Message Input Box -->
				<textarea class="chatInputBox" id="messageToSend"
					placeholder="Enter your message here..."></textarea>
				<!-- Buttons -->
				<div class="chatSendArea row align-items-center">
					<button class="btn col font-weight-bold">Invite</button>
					<button
						class="fas fa-laugh btn font-weight-bold chatInputEmojiButton"></button>
					<button id="sendButton" class="btn col font-weight-bold"
						onclick="sendMessage();">Send</button>
				</div>
			</div>

			<!-- Chat Box Script -->
			<script>
				// Your web app's Firebase configuration
				var _firebaseConfig = {
				    apiKey: "AIzaSyDvbLAmCUFiA94jVoh24h3M2Zw1REi1QjM",
				    authDomain: "cs201-project-c4168.firebaseapp.com",
				    databaseURL: "https://cs201-project-c4168.firebaseio.com/",
				    projectId: "cs201-project-c4168",
				    storageBucket: "cs201-project-c4168.appspot.com",
				    messagingSenderId: "747247541467",
				    appId: "1:747247541467:web:5efdb1140dca08111ec0fe",
				    measurementId: "G-TRH6PVHYGW"
				};
				// Initialize Firebase
				firebase.initializeApp(_firebaseConfig);
				
				var _state = {
					firechat: null,
					currUser: null,
					hasLoaded: false,
					roomId: null
				};
				
				function initChat(user) {
					// Get a Firebase Database ref
					var chatRef = firebase.database().ref("chat");

					// Create a Firechat instance
					var firechat = new Firechat(chatRef);
					firechat.on("room-enter", onRoomEntered);
					firechat.on("message-add", onReceiveMessage);
					// Set the Firechat user
					firechat.setUser(user.uid, user.displayName,
						function(userRef) {
							//firechat.resumeSession();
							_state.currUser = userRef;
							updateUserProfile(userRef, user.photoURL);
						});
					_state.firechat = firechat;
				}
				
				function updateUserProfile(user, photoUrl) {
					var chatRef = firebase.database().ref("chat/users/" + user.id);
			        chatRef.update({
			        	photoUrl: photoUrl
			        }, function(error) {
			            if (error) {
			                alert("Failed: " + JSON.stringify(error));
			            } else {
			                getUsers();
			            }
			        });
				}
				
				function onContactClicked(event) {
					var li = event.currentTarget;
					if(li.nodeName.toUpperCase() != "LI") {
						alert("Unknown node clicked: " + li.outerHTML);
						return;
					}
					
					chatWithFriend(li.id);
				}
				
				function chatWithFriend(friendId) {
					var roomName;
					if(friendId < _state.currUser.id) {
						roomName = friendId + " " + _state.currUser.id;
					}
					else {
						roomName = _state.currUser.id + " " + friendId;
					}
					createOrEnterRoom(roomName);
				}
				
				function getUsers() {
			    	var chatRef = firebase.database().ref("chat/users");
			        chatRef.on("value", function(snapshot) {
			        	var firstFriendId = null;
			        	if(!_state.hasLoaded) {
				    		var div = document.getElementById("contactList");
				        	for(var user in snapshot.val()) {
				        		// filter for friends
				        		
				        	    var userInfo = snapshot.val()[user];
				        	    if(_state.currUser.id == userInfo.id) {
				        	    	continue;
				        	    }
				        	    if (!firstFriendId) {
				        	    	firstFriendId = userInfo.id;
				        	    }
								var li = document.createElement("li");
								li.id = userInfo.id;
								li.className = "chatListRow row d-flex flex-row align";
								li.onclick = onContactClicked;
								var divProfile = document.createElement("div");
								divProfile.className = "chatListProfile";
								var img = document.createElement("img");
								img.className = "chatListProfilePicture rounded-circle";
								img.width = "20";
								if(userInfo.photoUrl)
								{							
									img.src = userInfo.photoUrl;
								}
								else {
									img.src = "timmy.png";
								}
								
								var name = document.createTextNode(userInfo.name);
								name.className = "chatListName";
								
								divProfile.appendChild(img);
								li.appendChild(divProfile);
								li.appendChild(name);
								div.appendChild(li);
				        	}
				        	_state.hasLoaded = true;
				        	
				        	if (firstFriendId){
				        		chatWithFriend(firstFriendId);
				        	}
			        	}
			        });
			    }	
				
				function onRoomEntered(roomId) {
					_state.roomId = roomId.id;
					_state.firechat.getRoom(roomId.id, function(room) {
						try {
							console.log("onRoomEntered: " + room.name);
							var roomName = room.name;
							var ids = roomName.split(" ");
							var friendId;
							if(ids[0] == _state.currUser.id) {
								friendId = ids[1];
							}
							else if (ids[1] == _state.currUser.id) {
								friendId = ids[0];
							}
							else {
								alert("Something went wrong!\n " + roomName);
								return;
							}
							var li = document.getElementById(friendId);
							var friendName = li.textContent;
							document.getElementById("chatHeaderRecipient").innerText = friendName;
						}
						catch (exception) {
							console.error("room enter exception", e.stack);
						}
					});
					
				}

				function sendTest(message) {
					// TODO: dynamic roomId generation
					_state.firechat.sendMessage(_state.roomId, message,
							messageType = 'default', function(foo) {
						
							});
				}
				
				function cleanRoom() {
					if (_state.roomId){
						document.getElementById("chatLog").innerHTML = "";
						_state.firechat.leaveRoom(_state.roomId);
						_state.roomId = null;
					}
				}
				
				function createOrEnterRoom(roomName) {
					_state.firechat.getRoomList(function(roomList) {
						var roomId = null;
						for(var id in roomList) {
							var g = roomList[id];
							if (g.name == roomName) {
								roomId = id;
								found = true;
								break;
							}
						}						
						
						if (_state.roomId) {
							if (roomId == _state.roomId) {
								console.log("Room unchanged: " + roomId);
								return;
							}
							else {
								cleanRoom();
							}
						}
							
						if(!roomId) {
							_state.firechat.createRoom(roomName, "public", function(roomId) {
							});
						}
						else {
							console.log("Entering room: " + roomId);
							_state.firechat.enterRoom(roomId);
						}
					});
					
				}
				
				
				function onReceiveMessage(roomId, message) {
					if(message.userId == _state.currUser.id) {
						displayMyMessage(message);
					}
					else {
						displayOtherMessage(message);
					}
				}
				
				function displayMyMessage(message) {
					var msg = "<li class='list-group-item' style='text-align: right'>"
						+ "<text class='userMessage'>"
						+ message.message
						+ "</text></li>";
					document.getElementById("chatLog").innerHTML += msg;
				}
				
				function displayOtherMessage(message) {
					var otherUser = message.userId;
			    	var chatRef = firebase.database().ref("chat/users/" + otherUser);
			        chatRef.on("value", function(snapshot) {
			        	var userInfo = snapshot.val();
			        	var li = document.createElement("li");
			        	li.className = "chatLogRow row d-flex flex-row";
			        	var profileDiv = document.createElement("div");
			        	profileDiv.className = "senderProfile";
			        	li.appendChild(profileDiv);
			        	var img = document.createElement("img");
			        	img.className = "senderProfilePicture rounded-circle";
			        	if (userInfo.photoUrl) {
			        		img.src = userInfo.photoUrl;
			        	}
			        	else {
			        		img.src = "timmy.png";
			        	}
			        	profileDiv.appendChild(img);
			        	var messageDiv = document.createElement("div");
			        	messageDiv.className = "senderMessage";
			        	li.appendChild(messageDiv);
			        	var messageText = document.createElement("text");
			        	messageText.innerText = message.message;
			        	messageDiv.appendChild(messageText);
						document.getElementById("chatLog").appendChild(li);
			        });
				}
				
				function login() {
			        // Log the user in via Google
			        var provider = new firebase.auth.GoogleAuthProvider();
			        firebase.auth().signInWithPopup(provider).then(function(result) {
				        // Once authenticated, instantiate Firechat with the logged in user
				        if (result.user) {
				          initChat(result.user);
				        }
			        }).catch(function(error) {
			          console.log("Error authenticating user:", error);
			        });
			        
			       // firebase.auth().onAuthStateChanged();
			    }
				
				login();
				
				
				/*
				firebase.auth().onAuthStateChanged(function(user) {
					if (user) {
						initChat(user);
						// User is signed in.
					} else {
						// No user is signed in.
					}
				});
				*/

				//_state.firechat.resumeSession();

				/* listen for "Enter" key press when chat box open */
				$("#messageToSend").keypress(function(event) {
					if (event.keyCode === 13) {
						event.preventDefault(); // disable new line function of enter key
						$("#sendButton").click(); // send message
					}
				});
				/* listen for "Enter" key press to search contact*/
				$("#searchContactBox").keypress(function(event) {
					if (event.keyCode === 13) {
						/* add implementation here to search for contact using keyword */
					}
				});
				function sendMessage() {
					var msg = document.getElementById("messageToSend").value;
					if (msg != "") {
						sendTest(msg);

						//displayMyMessage(msg);

						document.getElementById("messageToSend").value = '';
						document.getElementById("chatLog").scrollTop = document
								.getElementById("chatLog").scrollHeight;
						document.getElementById("messageToSend").placeholder = "Enter your message here..."
					} else {
						document.getElementById("messageToSend").placeholder = "Cannot send empty message! :("
					}
				}

				function addSampleContact(user) {
					var list = $('#chatList')[0];

				}
				
				document.getElementById("chatBox").style.visibility = "visible";
			</script>


		</div>


		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Scroll to Top Button-->
			<a class="scroll-to-top rounded" href="#page-top"> <i
				class="fas fa-angle-up"></i>
			</a>

			<!-- Logout Modal-->
			<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Ready to
								Leave?</h5>
							<button class="close" type="button" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">Select "Logout" below if you are
							ready to end your current session.</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">Cancel</button>
							<a class="btn btn-primary" href="login.html">Logout</a>
						</div>
					</div>
				</div>
			</div>

			<!-- Bottom-right Chat Window Modal-->
			<div class="modal" id="ChatWindowModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true"
				style="position: fixed; bottom: 0px; right: 0px; margin: 0px;">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Chat with
								NAME</h5>
							<button class="close" type="button" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">This room is private and requires a
							password to be joined.</div>
						<form>
							<div class="form-group modal-body">
								<label for="exampleInputPassword1">Password</label> <input
									type="password" class="form-control" id="exampleInputPassword1"
									placeholder="Password">
							</div>
						</form>
						<div class="modal-footer">
							<button class="btn btn-primary" type="button"
								data-dismiss="modal">Submit</button>
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Bootstrap core JavaScript-->
			<script src="vendor/jquery/jquery.min.js"></script>
			<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

			<!-- Core plugin JavaScript-->
			<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

			<!-- Custom scripts for all pages-->
			<script src="js/sb-admin-2.min.js"></script>

			<!-- Page level plugins -->
			<script src="vendor/chart.js/Chart.min.js"></script>

			<!-- Page level custom scripts -->
			<script src="js/demo/chart-area-demo.js"></script>
			<script src="js/demo/chart-pie-demo.js"></script>
</body>

</html>