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

</head>
<body>
	<div class="panel-group" id="accordion">
		<%! int cnt = 0; %> 
		<%
			Map<String, List<JobResult>> sampledata = new HashMap<String, List<JobResult>>();
			List<JobResult> job = new ArrayList<JobResult>();
			JobResult j = new JobResult();
			j.setStatus("C");
			JobResult j1 = new JobResult();
			j1.setStatus("C");
			JobResult j2 = new JobResult();
			j2.setStatus("C");
			JobResult j3 = new JobResult();
			j3.setStatus("C");
			job.add(j); job.add(j1); job.add(j2); job.add(j3);
			sampledata.put("US", job);
			
			List<JobResult> job1 = new ArrayList<JobResult>();
			job1.add(j); job1.add(j1); job1.add(j2); job1.add(j3);
			sampledata.put("INDIA", job1);
		
			//for (Map.Entry<String, List<JobResult>> entry : DataStore
			//		.getMapInstance().entrySet()) {
				for (Map.Entry<String, List<JobResult>> entry : sampledata.entrySet()) {
				cnt++;
		%>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a class="accordion-toggle" data-toggle="collapse"
						data-parent="#accordion" href="#collapse<%= cnt %>"> Country: <%= entry.getKey() %> </a>
				</h4>
			</div>
			<div id="collapse<%= cnt %>" class="panel-collapse collapse">
				<div class="panel-body">
					<table>
						<% 
							for(JobResult result : entry.getValue()) {
						%>
						<tr><td><%= result.getStatus() %></td></tr>
						<% 
							}
						%>
					</table>
				</div>
			</div>
		</div>
		<%
			}
		%>

	</div>
</body>
</html>
