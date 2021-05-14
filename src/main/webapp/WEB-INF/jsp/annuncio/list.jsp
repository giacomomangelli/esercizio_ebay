<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista Annunci</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary " href="insert">Add New</a>
		    	<a href="search" class='btn btn-outline-secondary' >
			            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
			    </a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Testo Annuncio</th>
		                        <th>Prezzo</th>
		                        <th>Data</th>
		                        <th>Utente</th>
		                        <th>Categorie</th>
		                        <th>Annuncio Aperto</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<c:forEach items="${annunci_list_attribute }" var="annuncioItem">
								<tr>
									<td>${annuncioItem.testoAnnuncio }</td>
									<td>${annuncioItem.prezzo }</td>
									<td>${annuncioItem.dataPubblicazione }</td>
									<td>${annuncioItem.utente.nome } ${annuncioItem.utente.cognome }</td>
									<td>${annuncioItem.categorie }</td>
									<td>${annuncioItem.aperto }</td>
									<td>
										<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath }/annuncio/show/${annuncioItem.id }">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath }/annuncio/edit/${annuncioItem.id }">Edit</a>
										<a class="btn  btn-sm btn-outline-danger ml-2 mr-2" href="${pageContext.request.contextPath }/annuncio/delete/${annuncioItem.id }">Elimina</a>
										
									</td>
								</tr>
							</c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>