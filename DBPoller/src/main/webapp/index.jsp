<%@ page import="java.util.*"%>
<%@ page import="org.kamal.*"%>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css">

<script src="http://codeorigin.jquery.com/jquery-2.0.3.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

<script type="text/javascript">

    var myCars=[];

    function showstuff(boxid){
       document.getElementById(boxid).style.display="block";
    }
     
    function hidestuff(boxid){
       document.getElementById(boxid).style.display="none";
    }

    function toggle(item) {
        for (i=0;i<myCars.length;i++) {
            if (myCars[i] === $(item).attr("id")) {
                showstuff($(item).attr("id") + "-status");
            } else {
                hidestuff(myCars[i] + "-status");
            }
        }
    }

</script>

</head>
<body>
	<div class="container">
        <h1>Jobs by Country</h1> 
        <div class="row">
            <div class="col-md-3">
                <div class="list-group">
                  <a href="#" class="list-group-item active">
                    Countries
                  </a>

					<%! int cnt = 0; %> 
					<%
						Map<String, List<JobResult>> sampledata = new HashMap<String, List<JobResult>>();
						List<JobResult> job = new ArrayList<JobResult>();
						JobResult j = new JobResult();
						j.setStatus("C");
						JobResult j1 = new JobResult();
						j1.setStatus("S");
						JobResult j2 = new JobResult();
						j2.setStatus("C");
						JobResult j3 = new JobResult();
						j3.setStatus("P");
						job.add(j); job.add(j1); job.add(j2); job.add(j3);
						sampledata.put("us", job);
						
						List<JobResult> job1 = new ArrayList<JobResult>();
						job1.add(j); job1.add(j1); job1.add(j2); job1.add(j3);
						sampledata.put("india", job1);
			
						//for (Map.Entry<String, List<JobResult>> entry : DataStore
						//		.getMapInstance().entrySet()) {
						for (Map.Entry<String, List<JobResult>> entry : sampledata.entrySet()) {
						cnt++;
					%>
			
	                      <a href="#" class="list-group-item" id="<%= entry.getKey()%>" onclick="toggle(this);"> <%= entry.getKey() %> </a>
	                <% 
	                	}

	        		%>
                </div>
            </div>

			<div class="col-md-1" background-color="white">
			
			</div>

	        <div class="col-md-6">

		        <%
			        for (Map.Entry<String, List<JobResult>> entry : sampledata.entrySet()) {
					cnt++;
				%>
				<script type="text/javascript">
	        		myCars[<%= cnt %>] = "<%= entry.getKey() %>"; 
	        	</script>
	            <div id="<%= entry.getKey() %>-status" style="display:none">
	                <table>
	                    <tr><th>Status</th></tr>
	                    <%
	                    	for (JobResult jb : entry.getValue()) {
	                    %>
	                   		 <tr><td><%= jb.getStatus() %></td></tr>
	                    <%
	                    	}
	                    %>
	                </table>
	            </div>
	            <%
					}
	            %>


	        </div>

         </div>

     </div>

</body>
</html>
