<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
   <H2>Entre com os dados do usuário abaixo</H2>
   <f:form commandName="user" method="POST" cssClass="form-horizontal" role="form">
      <f:errors path="*">
         <div class="alert alert-warning">Ops, alguma coisa está errada. Corrija os erros
            encontrados e tente novamente.</div>
      </f:errors>
      <div class="form-group">
         <label class="control-label col-lg-2" for="nome">Nome</label>
         <div class="col-lg-10">
            <f:input path="nome" class="form-control" placeholder="Nome do Usuário" />
            <f:errors path="nome" element="div" cssClass="text-danger" />
         </div>
      </div>
      <div class="form-group">
         <label class="control-label col-lg-2" for="sexo">Sexo</label>
         <div class="col-lg-10">
            <c:forEach var="s" items="${generosSexo}">
               <label class="radio-inline"> <input type="radio" name="sexo" id="sexo"
                  value="${s}" ${s eq user.sexo ? 'checked':''}>${s.descricao}
               </label>
            </c:forEach>
            <f:errors path="sexo" element="div" cssClass="text-danger" />
         </div>
      </div>
      <div class="form-group">
         <label class="control-label col-lg-2" for="dataNascimento">Data de Nasc.</label>
         <div class="col-lg-10">
            <f:input path="dataNascimento" cssClass="form-control date-picker"
               placeholder="dd/mm/aaaa" data-date-format="dd/mm/yyyy" />
            <f:errors path="dataNascimento" element="div" cssClass="text-danger"></f:errors>
         </div>
      </div>
      <div class="form-group">
         <label class="control-label col-lg-2" for="file">Imagem</label>
         <div class="col-lg-10">
            <img id="imgUsuario" class="img-thumbnail"
               src="${empty user.imagem ? 'http://www.placehold.it/300x300&amp;text=Imagem': uploadManager.get(user.imagem)} "
               style="width: 300px;" />
            <f:hidden path="imagem" />
            <input id="file" name="file" type="file" data-url="${root}/upload" />
            <f:errors path="imagem" element="div" cssClass="text-danger"></f:errors>
         </div>
      </div>
      <input type="submit" value="Salvar" class="btn btn-success" />
      <a href="../users" class="btn btn-default">Voltar</a>
   </f:form>
   <script type="text/javascript">
				$(document).ready(function() {
					$('.text-danger').parent().parent().addClass('has-error');
					$('.text-danger').parent().addClass('has-error');
					$('.date-picker').datepicker();
					$('#file').fileupload({
						dataType : 'json',
						done : function(e, data) {
							$.each(data.result.files, function(index, file) {
								$('#imgUsuario').attr('src', file.url);
								$('#imagem').val(file.name);
							});
						}
					});
				});
			</script>
</t:template>