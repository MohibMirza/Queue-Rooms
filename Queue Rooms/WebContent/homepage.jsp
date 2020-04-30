<%@page import="b_end.fb_info"%>
<%@page import="b_end.fb_info_json"%>
<%@page import="b_end.firebase_info_getter"%>
<%@page import="add_to_firebase_stuff.User"%>
<%@page import="add_to_firebase_stuff.Room"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Dashboard</title>
  
  
  <script>
  
  function addfriend()
  {
	  var friend_id = document.getElementById("friend_id").value;
	  var dd="entered addfreind";
	  console.log(dd);
	  $.ajax({
			url: "add_friend",
			data: {
				f_id: friend_id,
				my_id: UserID_js,
				normal_login: "1",
				UserID: UserID_js,
				Username: "<%=(String)request.getAttribute("Username")%>"
				
			},
			success: function(result) {
				var cc = 'Entered sucess'
				console.log(cc);
				$("html").empty();
				console.log(result);
				location.reload(true);
			    //$("html").append(result);  
			    
			}
		});
	  
  }
  </script> 

  <script>
    var myVar = setInterval(TopbarUpdate, 4000);

    function TopbarUpdate() {
        $.ajax({
          url: "TopbarUpdate",
          data: {
          },
          success: function(result) {
            $("#TopbarDisplay").html(result);
          }
      });
        return false;
    }
  </script>
  
 <script type="text/javascript"> 
  <%
  String UserID=(String)request.getAttribute("UserID");
  String Username=(String)request.getAttribute("Username");
  String normal_login="";
  if(UserID==null || UserID.equals(""))
  {
	  UserID= (String)session.getAttribute("UserID");
	  Username = (String)session.getAttribute("Username");
	  normal_login= (String)session.getAttribute("normal_login");
  }
  else
  {
	  System.out.println("In the else block!");
	  session.setAttribute("UserID", UserID);
	  session.setAttribute("Username", Username);
	  session.setAttribute("normal_login", "1");
  }
  
  String access_token=(String)request.getParameter("access_token");
  String image_url="https://d17fnq9dkz9hgj.cloudfront.net/uploads/2018/04/Golden-Retriever-puppy_01.jpg";
  fb_info_json client_info=null;
  fb_info obj_Client_details=null;
  String flag=null;
  String fb_usr=(String)request.getAttribute("fb_name");
  String fb_email=(String)request.getAttribute("fb_email");
  String fb_id=(String)request.getAttribute("fb_id");		  
  String fb_pic_url=(String)request.getAttribute("fb_pic");	
  String is_fb=(String)request.getAttribute("is_fb");	
  List<String> User_f_list= new ArrayList<String>();
  String friend_name=(String)request.getAttribute("friend_name");
  
  if(normal_login.equals("") || normal_login==null)
  	normal_login=(String)request.getAttribute("normal_login");
  
  if(normal_login==null || normal_login.equals("") || is_fb!=null)
  {
	  UserID=fb_usr;
	  Username=fb_usr;
	  image_url=fb_pic_url;
  }
  else{
   User_f_list =  User.getFriends(UserID); //new  ArrayList<String>();
   session.setAttribute("UserID", UserID);
   session.setAttribute("Username", Username);
  }
  
  if(friend_name==null)
  {
	  friend_name="";
  }
  
   %>
var new_friend='<%=friend_name%>';
	


var UserID_js = "<%=UserID %>" ;
var test="<%=access_token %>";
if(!(UserID_js=="null"))
{console.log(UserID_js);
 console.log(test);	
}
else{
 UserID_js="<%=fb_usr %>";
var fb_emailid="<%=fb_email %>";
var fb_Uid="<%=fb_id %>"; 
var fb_pic_url="<%=fb_pic_url %>";

console.log(UserID_js);
console.log(fb_emailid);
console.log(fb_Uid);
console.log(fb_pic_url);
}

<% if(UserID==null || UserID.equals(""))
{
	UserID=fb_id;
}
	
	%>
	
	$(document).ready(function(){
		/* $("#Friends").append('<a class="collapse-item" href="blank.html">CSCI201L</a>'); */
		
		
		 <% for(String friend: User_f_list){ 
		 	String friend_username= User.getUser(friend); 
		 %>

		 var temp ='<%=friend_username%>';
		$("#Friends").append('<a class="collapse-item" href="blank.html">' + temp + '</a>');	
	<% } %> 
	});
	
	
</script> 

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

  <!-- custom.css includes styling for the chat box -->
  <link href="css/custom.css" rel="stylesheet"> 
</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">MySAL</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="homepage.jsp?UserID=<%=UserID%>&Username=<%=Username%>">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Home</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <!-- <div class="sidebar-heading">
        Interface
      </div> -->

            <!-- Log Out -->
      <li class="nav-item">
        <a class="nav-link" href="alert-center.html">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Alert Center</span></a>
      </li>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Settings</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Custom Components:</h6>
            <a class="collapse-item" href="change-password.html">Change password</a>
            <a class="collapse-item" href="cards.html">Cards</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-laugh-wink"></i>
          <span>Friends</span>
        </a>

        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded" id="Friends">
            <h6 class="collapse-header">My Friends:</h6>
            <a class="collapse-item" href="utilities-color.html">Colors</a>
            <a class="collapse-item" href="utilities-border.html">Borders</a>
            <a class="collapse-item" href="utilities-animation.html">Animations</a>
            <a class="collapse-item" href="utilities-other.html">Other</a>
          </div>
        </div>
      </li>

      <!-- Log Out -->
      <li class="nav-item">
        <a class="nav-link" href="index.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Log Out</span></a>
      </li>

            <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">
        <!-- START OF CHAT BOX CODES -->
        <!-- Chat Box Open Button (float on top of everything)-->
        <button onclick="openChatBox();"id="chatButton" class="btn btn-primary chatButton">Chat</button>
        <!-- Chat Box Component (float on top of everything) -->
        <div class="chatBoxContainer row" id="chatBox">
          
          <!-- Chat List (left-hand-side) -->
          <div class="col1">
            <ul id="chatList" class="chatList">
              <div class="chatListContainer">
                <!-- Search contact Textarea -->
                <div class="form-inline md-form form-sm mt-0" style="padding: 7px; padding-left: 10px;">
                  <i class="fas fa-search" aria-hidden="true"></i>
                  <input id="searchContactBox" class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search contact"
                    aria-label="Search">
                </div>
                <!-- Dynamic List of contacts -->
                <li class="chatListRow row d-flex flex-row align">
                  <div class="chatListProfile">
                    <img class="chatListProfilePicture rounded-circle" src=timmy.png>
                  </div>
                  <text class="chatListName">Timmy</text>
                </li>
                <li class="chatListRow row d-flex flex-row align">
                  <div class="chatListProfile">
                    <img class="chatListProfilePicture rounded-circle" src=timmy.png>
                  </div>
                  <text class="chatListName">Timmy</text>
                </li>
                <li class="chatListRow row d-flex flex-row align">
                  <div class="chatListProfile">
                    <img class="chatListProfilePicture rounded-circle" src=timmy.png>
                  </div>
                  <text class="chatListName">Timmy</text>
                </li>
                <li class="chatListRow row d-flex flex-row align">
                  <div class="chatListProfile">
                    <img class="chatListProfilePicture rounded-circle" src=timmy.png>
                  </div>
                  <text class="chatListName">Timmy</text>
                </li>

              </div>
              

            </ul>
          </div>
          <!-- Message Area (right-hand-side) -->
          <div class="col2">
            <!-- Header: showing current chat recipient name -->
            <div class="chatBoxHeader row">
              <custom name="chatHeaderRecipient" class="chatHeaderRecipient font-weight-bold">Timmy</custom>
              <div class="closeChatBoxButton"> 
                <i class="fa fa-times" style="z-index: 1" onclick="closeChatBox();"></i>
              </div>

              
            </div>
            <!-- Dynamic Chat Log Box -->
            <ul id="chatLog" class="chatLog list-group">
              <li class="chatLogRow row d-flex flex-row">
                <div class="senderProfile">
                  <img class="senderProfilePicture rounded-circle" src=timmy.png>
                </div>
                <div class="senderMessage">
                  <text>Hello! This is a sample message with a lot of text. If anyone knows how to have the profile picture align with the top of this paragraph please change the code.</text>
                </div>
              </li>
            </ul>
            <!-- Chat Message Input Box -->
            <textarea class="chatInputBox" id="messageToSend" placeholder="Enter your message here..."></textarea>
            <!-- Buttons -->
            <div class="chatSendArea row align-items-center">
              <button class="btn col font-weight-bold">Invite</button>
              <button onclick="sendTestMessage();" class="btn col font-weight-bold">Test</button>
              <button class="fas fa-laugh btn font-weight-bold chatInputEmojiButton"></button>
              <button id="sendButton" class="btn col font-weight-bold" onclick="sendMessage();">Send</button>
            </div>
          </div>
        </div>

        <!-- Chat Box Script -->
        <script>
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
          function sendMessage(){
            var msg = document.getElementById("messageToSend").value;
            if(msg != ""){
              msg = "<li class='list-group-item' style='text-align: right'>" + "<text class='userMessage'>" + msg +"</text></li>";
              document.getElementById("chatLog").innerHTML += msg;
              document.getElementById("messageToSend").value = '';
              document.getElementById("chatLog").scrollTop = document.getElementById("chatLog").scrollHeight;
              document.getElementById("messageToSend").placeholder = "Enter your message here..."
            } else{
              document.getElementById("messageToSend").placeholder = "Cannot send empty message! :("
            }
          }
          function openChatBox(){
            document.getElementById("chatBox").style.visibility = "visible";
            document.getElementById("chatButton").style.visibility = "hidden";
          }
          function closeChatBox(){
            document.getElementById("chatBox").style.visibility = "hidden";
            document.getElementById("chatButton").style.visibility = "visible";
          }
          function sendTestMessage(){
            var testMsg;
            testMsg = "<li class='chatLogRow row d-flex flex-row'> <div class='senderProfile'> <img class='senderProfilePicture rounded-circle' src=timmy.png> </div> <div class='senderMessage'><text>This is a test message.</text></div></li>"
            document.getElementById("chatLog").innerHTML += testMsg;
          }
          function addSampleContact(user){
            var list = $('#chatList')[0];
            
          }
        </script>
        <!-- END OF CHAT BOX CODES -->
        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>


          <!-- Topbar Search -->
 
          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </li>

            <!-- Nav Item - Alerts -->
            <li id="TopbarDisplay" class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <%
                String userid2 = (String) session.getAttribute("UserID");
                //String userid2 = "I9dH5BF59AfcO5SPtZHCy2VhAwA3";
                        
                List<Alert> userAlerts2 = new ArrayList<Alert>();
                userAlerts2= Alert.findAlerts(userid2);
                try {
              Thread.sleep(4000);
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
                %>     
                <span class="badge badge-danger badge-counter"><%=userAlerts2.size()%></span>
              </a>
              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">                
                  <h6 class="dropdown-header">
                    Alerts Center
                  </h6>
                  <%
                  for (int i=0; i<userAlerts2.size(); i++) {   
                  %>
                    <a class="dropdown-item d-flex align-items-center" href="#">
                      <div class="mr-3">
                        <div class="icon-circle bg-primary">
                          <i class="fas fa-file-alt text-white"></i>
                        </div>
                      </div>
                      <div>
                        <div class="small text-gray-500"><%=userAlerts2.get(i).sender%></div>
                        <span class="font-weight-bold"><%=userAlerts2.get(i).message%></span>
                      </div>
                    </a>
                  <%}%>
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
              </div>
            </li>

            <!-- Nav Item - Messages -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" aria-haspopup="true" aria-expanded="false" onclick="toggleChatBox()">
                <i class="fas fa-envelope fa-fw"></i>
              </a>
            </li>


            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%= Username %></span>
                <img class="img-profile rounded-circle" src=<%= image_url %>>
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Profile
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                  Settings
                </a>
                <a class="dropdown-item" href="#">
                  <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                  Activity Log
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Logout
                </a>
                <div class="dropdown-divider"></div>
                <i class="dropdown-item" href="#" data-toggle="modal">
                  User ID: <%=UserID %>
                </i>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-gray-800">Quick Access</h1>
            </div>

			<div class="d-sm-flex align-items-center justify-content-between mb-2">
                <h1 class="h3 mb-0 text-gray-800">Add Friends!</h1>
                <div class="input-group mb-3">
  					<input type="text" class="form-control" placeholder="Enter a UserID" id="friend_id">
  					<div class="input-group-append">
    				<button class="btn btn-success" onclick="addfriend()">Add</button>
  				</div>
				</div>
            </div>
			
            <!-- Content Row -->
            <div class="row">

                <!-- Current Room Card -->
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Current Room</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">Next in line for CSCI201 Lab Th 2pm</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Pending Tasks Card -->
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-warning shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Calendar Reminder</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">2:00PM Today: CSCI201 Lab</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-comments fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tasks</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                        </div>
                        <div class="col">
                          <div class="progress progress-sm mr-2">
                            <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            </div>


            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-2">
                <h1 class="h3 mb-0 text-gray-800">Today at SAL</h1>
            </div>

            <!-- Page Heading Description-->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h2 class="h6 mb-0 text-gray-800">Classes and events taking place here today.</h2>
            </div>


                <!-- Color System -->
                <div class="row">
                    <div class="col-lg-2 mb-4">
                        <div class="card bg-primary text-white shadow">
                            <div class="card-body">
                                CSCI201 Lab
                                <div class="text-white-50 small">Th 2pm</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 mb-4">
                        <div class="card bg-success text-white shadow">
                            <div class="card-body">
                                CSCI103 Lab
                                <div class="text-white-50 small">Th 11am</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 mb-4">
                        <div class="card bg-info text-white shadow">
                            <div class="card-body">
                                CSCI104 Lab
                                <div class="text-white-50 small">Th 1pm</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 mb-4">
                        <div class="card bg-warning text-white shadow">
                            <div class="card-body">
                                ITP300 Lecture
                                <div class="text-white-50 small">TTh 3pm</div>
                            </div>
                        </div>
                    </div>

                </div>
            

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-2">
                <h1 class="h3 mb-0 text-gray-800">Rooms</h1>
            </div>

            <!-- Page Heading Description-->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h2 class="h6 mb-0 text-gray-800">You can join or create a room for class or private discussion. <a href="rui-rooms.html">See all rooms</a></h2>
            </div>


                <!-- Begin Table -->
          <div class ="row">
            <div class ="col-lg-12 mb-4">
              <div class="card shadow">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Rooms</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                      <thead>
                        <tr>
                          <th>Class</th>
                          <th>Location</th>
                          <th>Description</th>
                          <th>Host</th>
                          <th>Queue</th>
                          <th>Members</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>CSCI 201</td>
                          <td>SAL 109</td>
                          <td>CSCI 201 Lab Check off</td>
                          <td>Tommy Trojan</td>
                          <td><a href="room.html">Join</a></td>
                          <td>Invite</td>
                        </tr>
                        <tr>
                          <td>CSCI 270</td>
                          <td>SAL 103</td>
                          <td>CSCI 270 Office Hours</td>
                          <td>Tammy Trojan</td>
                          <td>Join</td>
                          <td>Invite</td>
                        </tr>
                        <tr>
                          <td>CSCI 104</td>
                          <td>SAL 110</td>
                          <td>CSCI 104 Assignment 2 OH</td>
                          <td>CP Traveller</td>
                          <td><a href="#JoinPrivateRoomModal" data-toggle="modal">Join</a></td>
                          <td>Invite</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
              
            
        </div>

       


        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->
    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="index.jsp">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Join Private Room Modal-->
  <div class="modal fade" id="JoinPrivateRoomModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Joining Private Room</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">This room is private and requires a password to be joined.</div>
        <form>
          <div class="form-group modal-body">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
          </div>
        </form>
        <div class="modal-footer">
          <button class="btn btn-primary" type="button" data-dismiss="modal">Submit</button>
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
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
