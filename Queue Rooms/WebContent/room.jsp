<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>mySAL - Room - CSCI201 </title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

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
              <div class="sidebar-brand-text mx-3">mySAL</div>
          </a>

          <!-- Divider -->
          <hr class="sidebar-divider my-0">

          <!-- Nav Item - Dashboard -->
          <li class="nav-item active">
              <a class="nav-link" href="index.html">
                  <i class="fas fa-fw fa-home"></i>
                  <span>Home</span>
              </a>
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider">

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
              <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                  <i class="fas fa-fw fa-user"></i>
                  <span>Friends</span>
              </a>
              <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                  <div class="bg-white py-2 collapse-inner rounded">
                      <h6 class="collapse-header">Favorites:</h6>
                      <a class="collapse-item" href="blank.html">Timmy Nook</a>
                      <a class="collapse-item" href="blank.html">Tommy Nook</a>
                  </div>
              </div>
          </li>

          <!-- Nav Item - Tables -->
          <li class="nav-item">
            <a class="nav-link" href="rui-rooms.html">
              <i class="fas fa-fw fa-table"></i>
              <span>Rooms</span></a>
          </li>

          <!-- Nav Item - Tables -->
          <li class="nav-item">
            <a class="nav-link" href="settings.html">
              <i class="fas fa-fw fa-cog"></i>
              <span>Settings</span></a>
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider d-none d-md-block">

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

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
            <div class="input-group">
              <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
                <button class="btn btn-primary" type="button">
                  <i class="fas fa-search fa-sm"></i>
                </button>
              </div>
            </div>
          </form>

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
                <!-- Counter - Messages -->
                <!-- <span class="badge badge-danger badge-counter"></span> -->
              </a>
              <!-- Dropdown - Messages -->
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Valerie Luna</span>
                <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
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
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">CSCI 201</h1>

          <div class="row">
            <div class="col-lg-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Description</h6>
                </div>
                <div class="card-body">
                  Please come to the front of the room for check off when it's your turn. Please arrive min. 5 mins early.
                  </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-lg-6 mb-4">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <div class="row align-items-center">
                    <div class = "col-sm">
                      <h6 class="m-0 font-weight-bold text-primary mb-2">Queue</h6> 
                    </div>
                    <div class = "row-right">
                      <!-- Join Queue Button triggers modal-->
                      <a href="#JoinQueueModal" class="btn btn-primary" data-toggle="modal">Join</a>
                      <a href="#QuitQueueModal" class="btn btn-warning" data-toggle="modal">Cancel</a>
                    </div>
            
                  </div>
                  
                </div>

                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>Name</th>
                          <th>Estimate Wait Time</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>Abby B</td>
                          <td>5 mins</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Bobby C</td>
                          <td>15 mins</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Carly D</td>
                          <td>25 mins</td>
                        </tr>
                        <tr>
                          <td>4</td>
                          <td>Danny E</td>
                          <td>35 mins</td>
                        </tr>
                        <tr>
                          <td>5</td>
                          <td>Edward F</td>
                          <td>45 mins</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>


                </div>
              </div>
            </div>

            <div class="col-lg-6 mb-4">
			
			  <canvas id = "drawCanvas" width = "800" height = "400" style="border:1px solid #000000;"></canvas> <!--width = "800" height = "600"-->
			  <button onclick="stopErase();">Draw</button>
			  <button onclick="startErase();">Erase</button>
			  
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Message Board</h6>
                </div>
                <div class="card-body">


                    <div class="font-weight-bold">
                      <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been having.</div>
                      <div class="small text-gray-500">Emily Fowler · 18m</div>
                    </div>

                    <br>

                    <div class="font-weight-bold">
                      <div class="text-truncate">Where is everyone sitting?</div>
                      <div class="small text-gray-500">CP Tommy · 20m</div>
                    </div>

                    <br>

                    <div class="font-weight-bold">
                      <div class="text-truncate">Make sure to check Piazza for tips!</div>
                      <div class="small text-gray-500">CP Tommy · 32m</div>
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
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Join Queue Modal-->
  <div class="modal fade" id="JoinQueueModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Join Queue Successful!</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">If you want to cancel your position, click the "Quit" button next to the queue</div>
        <div class="modal-footer">
          <button class="btn btn-primary" type="button" data-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Quit Queue Modal-->
  <div class="modal fade" id="QuitQueueModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Confirm quiting queue</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">If you leave the queue now, your position will be lost and you will have to join again from the end of the queue.</div>
        <div class="modal-footer">
          <button class="btn btn-primary" type="button" data-dismiss="modal">Quit</button>
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
        </div>
      </div>
    </div>
  </div>

<div id="chatBoxDiv" style="position:absolute;top:100px;right:100px; width:600px;height:640px;visibility:hidden">
	<iframe src="chat_widget.jsp" style="float:right; width:100%; height:100%"></iframe>
</div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Custom script from frontend-zi-->
  <script src="js/frontend-zi.js"></script>
  
  <script src="https://www.gstatic.com/firebasejs/7.14.2/firebase-app.js"></script>
  <script src="https://www.gstatic.com/firebasejs/3.3.0/firebase.js"></script>


<script type="text/javascript">
	
	var firebaseConfig = {
		apiKey: "AIzaSyDCm3yLjNFmA_irCAmk54tQVmr8lhf1PJc",
		authDomain: "mytest1-ab6ae.firebaseapp.com",
		databaseURL: "https://mytest1-ab6ae.firebaseio.com",
		projectId: "mytest1-ab6ae",
		storageBucket: "mytest1-ab6ae.appspot.com",
		messagingSenderId: "628828202676",
		appId: "1:628828202676:web:5a63c783999939d00719e1",
		measurementId: "G-H77E4RTWY5"
	};
	
	firebase.initializeApp(firebaseConfig);
	
	
	var database = firebase.database();
	
	var _canvasRef = database.ref().child('canvas');
	
	
	var canvas = document.getElementById('drawCanvas');
	var ctx = canvas.getContext('2d');
	ctx.strokeStyle = "black";
	var isErase = false;
	
	canvas.addEventListener('mousedown', startDraw, false);
	canvas.addEventListener('mousemove', draw, false);
	canvas.addEventListener('mouseup', endDraw, false);
	// canvas.addEventListener('mouseout', endDraw, false);
	
	var isActive = false;
	
	var plotX = [];
	var plotY = [];
	var plotErase = [];
	
	addImage();
	
	function draw(e) {
		if(!isActive) return;
		
		var rect = canvas.getBoundingClientRect();
		
		//var x = e.clientX - canvas.offsetLeft;
		//var y = e.clientY - canvas.offsetTop;
		
		var x = e.clientX - rect.left;
		var y = e.clientY - rect.top;
		
		plotX.push(x);
		plotY.push(y);
		plotErase.push(isErase);
		
		drawOnCanvas(plotX, plotY);
	}
	
	function drawOnCanvas(xplot, yplot) {
		if(isErase) {
			startErase();
		} else {
			stopErase();
		}
		ctx.beginPath();
		
		if(!xplot.length || !yplot.length) return;
		
		ctx.moveTo(xplot[0], yplot[0]);
		for(var i=1; i<xplot.length; i++) {
			ctx.lineTo(xplot[i], yplot[i]);
		}
		
		ctx.stroke();
	}
	
	function startDraw(e) {
		/*
		prevX = currX;
		prevY = currY;
		currX = e.clientX - canvas.offsetLeft;
		currY = e.clientY - canvas.offsetTop;
		*/
		isActive = true;
	}
	
	function endDraw(e) {
		isActive = false;
		plotX = [];
		plotY = [];
		plotErase = [];
		writeCanvas();
	}

	function startErase(e) {
		ctx.strokeStyle = "white";
		isErase = true;
		ctx.lineWidth = '20';
	}
	
	function stopErase(e) {
		ctx.strokeStyle = "black";
		isErase = false;
		ctx.lineWidth = '1';
	}

	function serialize(canvas) {
		return canvas.toDataURL();
	}
	
	function deserialize(data, canvas) {
		var img = new Image();
		img.onload = function() {
			canvas.width = img.width;
			canvas.height = img.height;
			console.log("nice");
			canvas.getContext("2d").drawImage(img, 0, 0);
		};

		img.src = data;
	}
	
	function addImage() {
		var pictureCanvasId = "drawCanvas";
		var canvas1 = document.getElementById(pictureCanvasId);
		_canvasRef.on('value',function(snapshot) {
			deserialize(snapshot.val().canvas, canvas1);
		})
		
	}
	
	function writeCanvas(e) {
		var canvasContent = serialize(canvas);
		_canvasRef.set({
			canvas: canvasContent
		}, function(e){
			
		});
		
	}
	
</script>


	<script>
		var isHidden = true;
		function toggleChatBox() {
			isHidden = !isHidden;
			if(!isHidden) {
				document.getElementById("chatBoxDiv").style.visibility = "visible";
			}
			else {
				document.getElementById("chatBoxDiv").style.visibility = "hidden";
			}
			
			
		}
	</script>

</body>

</html>