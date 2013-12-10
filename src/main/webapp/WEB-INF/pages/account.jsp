<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
   <H2>Entre com os dados da conta</H2>
   <f:form commandName="account" method="POST" cssClass="form-horizontal" role="form">
      <f:errors path="*">
         <div class="alert alert-warning">Ops, alguma coisa está errada. Corrija os erros
            encontrados e tente novamente.</div>
      </f:errors>
      <div class="form-group">
         <label class="control-label col-lg-2" for="user">Cliente</label>
         <div class="col-lg-10">
            <f:hidden path="user.id" />
            <f:input path="user.nome" cssClass="form-control" />
            <f:errors path="user.id" element="div" cssClass="text-danger" />
         </div>
      </div>
      <div class="form-group">
         <label class="control-label col-lg-2" for="balance">Nr. Conta</label>
         <div class="col-lg-10">
            <f:input path="bankAccount" type="number" class="form-control col-lg-2"
               placeholder="Número da Conta" />
            <f:errors path="bankAccount" element="div" cssClass="text-danger" />
         </div>
      </div>
      <div class="form-group">
         <label class="control-label col-lg-2" for="balance">Saldo</label>
         <div class="col-lg-10">
            <div class="input-group">
               <span class="input-group-addon">R$</span>
               <f:input path="balance" class="form-control" placeholder="0,00" />
            </div>
            <f:errors path="balance" element="div" cssClass="text-danger" />
         </div>
      </div>
      <div class="form-group">
         <label class="control-label col-lg-2" for="user">Tipo da Conta</label>
         <div class="col-lg-10">
            <f:select path="accountType" cssClass="form-control">
               <f:option value="${null}">Selecione um tipo de conta</f:option>
               <f:options items="${accountTypes}" itemLabel="name" itemValue="id" />
            </f:select>
            <f:errors path="accountType" element="div" cssClass="text-danger" />
         </div>
      </div>
      <input type="submit" value="Salvar" class="btn btn-success" />
      <a href="../accounts" class="btn btn-default">Voltar</a>
   </f:form>
   <script type="text/javascript">
				$(document).ready(function() {
					$('.text-danger').parent().parent().addClass('has-error');
					$('.text-danger').parent().addClass('has-error');
					$('#bankAccount').inputmask("integer");
					$('#balance').inputmask('decimal', {
						radixPoint : ",",
						digits : 2,
						autoGroup : true,
						groupSeparator : ".",
						groupSize : 3
					});
					$('#user\\.nome').autocomplete({
						serviceUrl : '/cbsa/users',
						dataType : 'json',
						onSelect : function(suggestion) {
							$('#user\\.id').val(suggestion.data);
						},
						transformResult : transformAutoComplete
					});
					$('#accountType').chosen({});
				});
				function transformAutoComplete(response) {
					return {
						suggestions : $.map(response.users, function(dataItem) {
							return {
								value : dataItem.nome,
								data : dataItem.id
							};
						})
					};
				}
			</script>
</t:template>