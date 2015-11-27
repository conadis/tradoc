<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link rel="stylesheet" href="../css/validationEngine.jquery.css" type="text/css">

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->


                      
            <script type="text/javascript">
            
			    $(document).ready(function() {
			    
			    	jQuery("#frmAgregarExpediente").validationEngine({});
			    	
					window.prettyPrint() && prettyPrint();
				
                    
                    
			    });

				$(function() {
					  $('#boxclose').click(function(){
	                        $('#box').animate({'top':'30%'},500,function(){
	                            $('#overlay').fadeOut('fast');
	                        });
	                    });
	                    
	                    $('#cancel').click(function(){
	                        $('#box').animate({'top':'30%'},500,function(){
	                            $('#overlay').fadeOut('fast');
	                        });
	                    });
					
	                    $('#cmbRemitenteAdd').multiselect({
						    includeSelectAllOption: true,
							enableCaseInsensitiveFiltering: true, 
							buttonClass: 'validate[required] multiselect dropdown-toggle btn btn-default'
						});
						$('.btn-group button').val($('#cmbRemitenteAdd').val());
						
	                    
	                   
	                    $('#guardar').click(function(){
	
	                    	jQuery("#frmAgregarExpediente").validationEngine('validate');
	    
						 		$('#frmAgregarExpediente').ajaxForm({url:"../addExpediente.htm",type:"post", success:function(data){
						 					
						 					if(data =='false'){
						 						alert("Ya existe el expediente, consulte con el administrador");
						 					}else{
						 						 data = JSON.parse(data)
						 						
						 						
						 						alert("Expediente guardado correctamente");
						 						 $("#hdnCodExpediente").val("");
												 $("#txtExpAnioBus").val(data.numeracionDocumento.anioDocumento); 
												 $("#txtExpNumeBus").val(data.numeracionDocumento.numDocumento); 
												 $("#cmbTipoExpBus").val(data.numeracionDocumento.indExpediente);
												 $("#txtFechaIngresoBus").val(data.fecCreacion);
												 $("#txtCreadoPorBus").val(data.codUsuCreacion); 
												 $("#txtDocumentoBus").val(data.desDocumento);
												 $("#txtAsuntoBus").val(data.desAsunto);
											     $("#txtInteresadoBus").val(data.interesado);//falta
											     $("#txtUtdBus").val(data.desUtd);
											     $("#txtDetalleBus").val(data.detalle);
											     $("#txtDescripcionBus").val(data.descripcion);
											     $("#txtNumFolBus").val(data.numFolios);
											     $("#txtDiasBus").val(data.canDias);
											     $("#cmbRemitenteBus").val(data.entidadExterna.codEntidad);
											     $("#hdnCodEstadoExpediente").val(data.estadoExpediente.codEstadoExpediente);
											     $("#grdDocExt").show();
											     
												$('#box').animate({'top':'100px'},500,function(){
						                            $('#overlay').fadeOut('fast');
					                        	});
			                    	}
			                    	}
			                    
			                    });
								
							 
	                    });



                });
				
				
                
			
			</script>
</head>
<body>

				
                <div  class="overlay" id="overlay" style="display:none;">
                	<div class="box" id="box" style="width:800px;top: 18%;">
                    <a class="boxclose" id="boxclose" onclick="cerrar();"></a>
					<c:choose>
					    <c:when test="${!empty expediente.codCorrelativo}">
					        <div id="title-header-popup">Editar Expediente</div>
					    </c:when>
					    <c:otherwise>
					        <div id="title-header-popup">Registrar Expediente</div>
					    </c:otherwise>
					</c:choose>
                        <form:form  id="frmAgregarExpediente" enctype="multipart/form-data" method="post" class="scrollbar" modelAttribute="expedienteVO">
                            <ul id="body-popup">
                             <fieldset  class="fieldset">
    						<legend>Registro Expediente:</legend>
                              <table align="center"  width="100%" border="0" cellpadding="3" cellspacing="1">
                                <tr>
                            	   <td>Expediente:</td>
                            	   <td>
                            	     <c:choose>
                               			<c:when test="${tipo == 'agregar'}">
											<table>
												<tr>
													<td><form:input type="number" value="2015"
																    style="width: 60px;" id="txtAnioAdd" path="anioDocumento" class="validate[custom[integer],required] input-popup-2"
																	onkeypress="validaSoloNumeros();" maxlength="4" min="2015"
																	max="2040" cssStyle="height: 25px;"/></td>
													<td><form:input id="txtNumExpAdd" path="numDocumento" style="width: 40px; height: 25px;" type="text" class="validate[custom[integer],required] input-popup-2" 
														onkeypress="validaSoloNumeros();" maxlength="3"/></td>
													<td><form:select id="cmbTipExpAdd"  path="indExpediente" style="width: 60px;height: 25px;"
														class="select-box">
															<form:option value="E" label="E" />
															<form:option value="I" label="I" />
													</form:select></td>
												</tr>
											</table>
									
		                             	</c:when>
		                               	<c:otherwise>
		                              		  <table>
												<tr>
													<td><form:input type="number" value="2015"
																    style="width: 60px;height: 25px;" id="txtAnioAddu" path="anioDocumento" class="validate[custom[integer],required] input-popup-2"
																	onkeypress="validaSoloNumeros();"  maxlength="4" min="2015" 
																	max="2040"/></td>
													<td><form:input id="txtNumExpAddu" path="numDocumento" 
														style="width: 40px; height: 25px;" type="text" class="validate[custom[integer],required] input-popup-2" 
														onkeypress="validaSoloNumeros();" maxlength="3" /></td>
													<td><form:select id="cmbTipExpAddu" path="indExpediente" style="width: 60px; height: 25px;" disabled="false"
														class="select-box">
															<form:option value="E" label="E" />
															<form:option value="I" label="I" />
													</form:select></td>
												</tr>
											</table>
		                               </c:otherwise>
                               		</c:choose>
                            	   </td>
                            	   <td>Fecha de Ingreso:</td>
									<td><form:input id="txtFecCreaAdd" path="fecCreacion" style="width: 80px;height: 25px;" type="text" readonly="true" /></td>
									<td>Creado Por:</td>
									<td><form:input id="txtCreaPorAdd" path="codUsuario" readonly="true" style="width: 80px;height: 25px;" type="text" class="input-border"/></td>
                                </tr>
                                <tr>
                            	   <td colspan="1">Documento:</td>
                            	   <td colspan="5">
                                     <form:input type="text" id="txtNombreAdd" class="validate[required] input-popup-3" cssStyle="height: 25px;" path="desDocumento"  maxlength="60"/>
                            	   </td>                         	   
                                </tr>                                
                                <tr>
                            	   <td>Remitente:</td>
                            	   <td colspan="5">
                            	   	<form:select id="cmbRemitenteAdd" path="codEntidad" disabled="false" cssStyle="height: 25px;"
														class="select-box">
															<form:option value="1" label="SUNAT" />
															<form:option value="2" label="MEF" />
															<form:option value="3" label="MINISTERIO VIVIENDA" />
													</form:select>
              
										
                            	   </td>
										
                                </tr>   
                                <tr> 
                            	   <td>Asunto:</td>
									<td><form:input id="txtAsuntoAdd" path="desAsunto" style="width: 200px;height: 25px;" type="text" class="validate[required] input-popup-2" maxlength="60"/></td>
									<td>Interesado:</td>
									<td colspan="3"><form:input id="txtInteresadoAdd" path="interesado" style="width: 200px;height: 25px;" type="text" class="validate[required] input-popup-2" maxlength="60"/></td>
                                </tr>          
                     			<tr>
                     				<td>UTD:</td>
									<td><form:input id="txtUtdAdd"  path="desUtd" style="width: 200px;height: 25px;" type="text" class="validate[required] input-popup-2" maxlength="60"/></td>
									<td>Detalle:</td>
									<td colspan="3"><form:input id="txtDetalleAdd" path="detalle" style="width: 200px;height: 25px;" type="text" class="input-border"/></td>
                                </tr>   
                     			<tr>
                     			
                     				<td>Descripci&oacute;n:</td>
									<td><form:textarea id="txtDescripcionExpAdd" path="descripcion" class="validate[required]"  style="resize: none;" rows="4" cols="20"></form:textarea></td>
									<td>Nro.Folios:</td>
									<td><form:input type="number" class="validate[required] input-popup-2" value="1" style="width: 60px;height: 25px;" id="txtNumFolAdd" path="numFolios" min="0" max="100"/></td>
									<td>Dias:</td>
									<td><form:input id="txtDiasExpAdd" class="validate[required] input-popup-2" path="canDias"  style="width: 70px;height: 25px;" type="text" /></td>
										
                                </tr>                                                                                                                       
                                                             
                              </table>
					</fieldset>
								
                            </ul>

                            <ul id="button-popup">
                            	<li><input type="submit" id="guardar"  value="Guardar" class="button-image-complete-gren" ></li>
                            	<li><input type="button" id="cancel" onclick="cerrar();" value="Cancelar" class="button-image-complete"><li>
                            </ul>
                        </form:form>


                    </div>
                </div>


</body>
</html>