<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
	<script src="https://www.gstatic.com/firebasejs/7.14.2/firebase-app.js"></script>
	<script src="https://www.gstatic.com/firebasejs/3.3.0/firebase.js"></script>
	<title>My Whiteboard</title>
</head>
<body>
	<canvas id = "drawCanvas" width = "800" height = "600" style="border:1px solid #000000;"></canvas>
	<button onclick="stopErase();">Draw</button>
	<button onclick="startErase();">Erase</button>
	<div id="canvasPicture">
		
	</div>
</body>
</html>


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
	
	function draw(e) {
		if(!isActive) return;
		
		var x = e.clientX - canvas.offsetLeft;
		var y = e.clientY - canvas.offsetTop;
		
		plotX.push(x);
		plotY.push(y);
		plotErase.push(isErase);
		
		drawOnCanvas(plotX, plotY);
	}
	
	function drawOnCanvas(xplot, yplot) {
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
		ctx.lineWidth = '10';
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
	        canvas.getContext("2d").drawImage(img, 0, 0);
	    };

	    img.src = data;
	}
	
	function addImage() {
		var div = document.getElementById("canvasPicture");
		var canvas1;
		if(div.children.length == 0){
			canvas1 = document.createElement("canvas");
			div.appendChild(canvas1);
		}
		else {
			canvas1 = document.children[0];
		}
		database.ref().child('canvas').on('value',function(snapshot) {
			deserialize(snapshot.val().canvas, canvas1);
		})
		
	}
	
	function writeCanvas(e) {
		database.ref().child('canvas').set({
			canvas: serialize(canvas)
		}, function(e){
			addImage();
		});
		
	}
	
</script>