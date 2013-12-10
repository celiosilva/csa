/**
 * Ativar e desativar menu lateral
 */
$(document).ready(function() {
	$('[data-toggle=offcanvas]').click(function() {
		$('.row-offcanvas').toggleClass('active');
	});
});

/**
 * Componentes CBSA
 */

function inputNumber(input) {
	$(input).attr('type', 'number');
	$(input).inputmask("integer");
}

function inputDecimal(input) {
	$(input).inputmask('decimal', {
		radixPoint : ",",
		autoGroup : true,
		groupSeparator : ".",
		groupSize : 3
	});
}

function inputDate(input) {
	$(input).inputmask("99/99/9999");
}

function inputMask(input, mask) {
	$(input).inputmask(mask);
}

function inputMoney(input) {
	$(input).inputmask('decimal', {
		radixPoint : ",",
		digits : 2,
		autoGroup : true,
		groupSeparator : ".",
		groupSize : 3
	});
}

function inputAutocomplete(input, url, convertData) {
	$(input).autocomplete({
		serviceUrl : url,
		dataType : 'json',
		onSelect : function(suggestion) {
			$(input).val(suggestion.data);
		},
		transformResult : convertData
	});
}

function select(input) {
	$(input).chosen({
		no_results_text : 'Nenhuma informação encontrada com '
	});
}

function inputImage(inputFile, hidden, img) {
	var imagem = $(img);
	if (imagem.attr('src') == null || imagem.attr('src') == '') {
		imagem.attr('src', 'http://www.placehold.it/400x400&text=Imagem');
	}
	$(inputFile).fileupload({
		dataType : 'json',
		done : function(e, data) {
			$.each(data.result.files, function(index, file) {
				$(img).attr('src', file.url);
				$(hidden).val(file.name);
			});
		}
	});
}

function inputFile(inputFile, hidden, fileLink) {
	var link = $(fileLink);
	if (link.attr('href') == null || link.attr('href') == '') {
		link.removeAttr('href');
	}
	$(inputFile).fileupload({
		dataType : 'json',
		done : function(e, data) {
			$.each(data.result.files, function(index, file) {
				$(fileLink).attr('href', file.url);
				$(fileLink).attr('download', file.name);
				$(hidden).val(file.name);
			});
		}
	});
}
