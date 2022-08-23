window.onload = function () {
    desabilita('nome');
    desabilita('rua');
    desabilita('complemento');
    desabilita('numero');
    desabilita('bairro');
    desabilita('cep');
    desabilita('cidade');
    desabilita('estado');
    desabilita('telefone_fixo');
    desabilita('telefone');
    desabilita('email');
    desabilita('nome_fant');

    if (window.location.href.indexOf("editar") > -1) {
        habilita('nome');
        habilita('rua');
        habilita('complemento');
        habilita('numero');
        habilita('bairro');
        habilita('cep');
        habilita('cidade');
        habilita('estado');
        habilita('telefone_fixo');
        habilita('telefone');
        habilita('email');
        habilita('nome_fant');
    }
};
function verificaNumeros(numero) {
    numero.value = numero.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
}

function pesquisacep(valor) {
    if (event.key === 'Enter') {
        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep !== "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if (validacep.test(cep)) {

                document.getElementById('cidade').value = "...";
                document.getElementById('estado').value = "...";
                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = '//viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            const div = document.getElementById('verificacaocep');
            div.textContent = "Informe um cep"
            document.getElementById('cep').value = ("");
        }
    }
}

function formatar(mascara, documento) {
    var i = documento.value.length;
    var saida = mascara.substring(0, 1);
    var texto = mascara.substring(i);

    if (texto.substring(0, 1) != saida) {
        documento.value += texto.substring(0, 1);
    }

}
function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById('cidade').value = (conteudo.localidade);
        document.getElementById('estado').value = (conteudo.uf);
        const div = document.getElementById('verificacaocep');
        div.textContent = ""
    } //end if.
    else {
        const div = document.getElementById('verificacaocep');
        div.textContent = "CEP invalido"
        document.getElementById('cep').value = ("");
    }
}

function verifica_cpf_cnpj(valor) {

    // Garante que o valor é uma string
    valor = valor.toString();

    // Remove caracteres inválidos do valor
    valor = valor.replace(/[^0-9]/g, '');

    // Verifica CPF
    if (valor.length === 11) {
        return 'CPF';
    }

    // Verifica CNPJ
    else if (valor.length === 14) {
        return 'CNPJ';
    }

    // Não retorna nada
    else {
        return false;
    }

} // verifica_cpf_cnpj

/*
calc_digitos_posicoes

Multiplica dígitos vezes posições

@param string digitos Os digitos desejados
@param string posicoes A posição que vai iniciar a regressão
@param string soma_digitos A soma das multiplicações entre posições e dígitos
@return string Os dígitos enviados concatenados com o último dígito
*/
function calc_digitos_posicoes(digitos, posicoes = 10, soma_digitos = 0) {

    // Garante que o valor é uma string
    digitos = digitos.toString();

    // Faz a soma dos dígitos com a posição
    // Ex. para 10 posições:
    //   0    2    5    4    6    2    8    8   4
    // x10   x9   x8   x7   x6   x5   x4   x3  x2
    //   0 + 18 + 40 + 28 + 36 + 10 + 32 + 24 + 8 = 196
    for (var i = 0; i < digitos.length; i++) {
        // Preenche a soma com o dígito vezes a posição
        soma_digitos = soma_digitos + (digitos[i] * posicoes);

        // Subtrai 1 da posição
        posicoes--;

        // Parte específica para CNPJ
        // Ex.: 5-4-3-2-9-8-7-6-5-4-3-2
        if (posicoes < 2) {
            // Retorno a posição para 9
            posicoes = 9;
        }
    }

    // Captura o resto da divisão entre soma_digitos dividido por 11
    // Ex.: 196 % 11 = 9
    soma_digitos = soma_digitos % 11;

    // Verifica se soma_digitos é menor que 2
    if (soma_digitos < 2) {
        // soma_digitos agora será zero
        soma_digitos = 0;
    } else {
        // Se for maior que 2, o resultado é 11 menos soma_digitos
        // Ex.: 11 - 9 = 2
        // Nosso dígito procurado é 2
        soma_digitos = 11 - soma_digitos;
    }

    // Concatena mais um dígito aos primeiro nove dígitos
    // Ex.: 025462884 + 2 = 0254628842
    var cpf = digitos + soma_digitos;

    // Retorna
    return cpf;

} // calc_digitos_posicoes

/*
Valida CPF

Valida se for CPF

@param  string cpf O CPF com ou sem pontos e traço
@return bool True para CPF correto - False para CPF incorreto
*/
function valida_cpf(valor) {

    // Garante que o valor é uma string
    valor = valor.toString();

    // Remove caracteres inválidos do valor
    valor = valor.replace(/[^0-9]/g, '');


    // Captura os 9 primeiros dígitos do CPF
    // Ex.: 02546288423 = 025462884
    var digitos = valor.substr(0, 9);

    // Faz o cálculo dos 9 primeiros dígitos do CPF para obter o primeiro dígito
    var novo_cpf = calc_digitos_posicoes(digitos);

    // Faz o cálculo dos 10 dígitos do CPF para obter o último dígito
    var novo_cpf = calc_digitos_posicoes(novo_cpf, 11);

    // Verifica se o novo CPF gerado é idêntico ao CPF enviado
    if (novo_cpf === valor) {
        // CPF válido
        return true;
    } else {
        // CPF inválido
        return false;
    }

} // valida_cpf

/*
valida_cnpj

Valida se for um CNPJ

@param string cnpj
@return bool true para CNPJ correto
*/
function valida_cnpj(valor) {

    // Garante que o valor é uma string
    valor = valor.toString();

    // Remove caracteres inválidos do valor
    valor = valor.replace(/[^0-9]/g, '');


    // O valor original
    var cnpj_original = valor;

    // Captura os primeiros 12 números do CNPJ
    var primeiros_numeros_cnpj = valor.substr(0, 12);

    // Faz o primeiro cálculo
    var primeiro_calculo = calc_digitos_posicoes(primeiros_numeros_cnpj, 5);

    // O segundo cálculo é a mesma coisa do primeiro, porém, começa na posição 6
    var segundo_calculo = calc_digitos_posicoes(primeiro_calculo, 6);

    // Concatena o segundo dígito ao CNPJ
    var cnpj = segundo_calculo;

    // Verifica se o CNPJ gerado é idêntico ao enviado
    if (cnpj === cnpj_original) {

        return true;
    }

    // Retorna falso por padrão
    return false;

} // valida_cnpj

/*
valida_cpf_cnpj

Valida o CPF ou CNPJ

@access public
@return bool true para válido, false para inválido
*/
function valida_cpf_cnpj(valor) {

    // Verifica se é CPF ou CNPJ
    var valida = verifica_cpf_cnpj(valor);

    // Garante que o valor é uma string
    valor = valor.toString();

    // Remove caracteres inválidos do valor
    valor = valor.replace(/[^0-9]/g, '');


    // Valida CPF
    if (valida === 'CPF') {
        // Retorna true para cpf válido
        return valida_cpf(valor);
    }

    // Valida CNPJ
    else if (valida === 'CNPJ') {
        return valida_cnpj(valor);
    }

    // Não retorna nada
    else {
        return false;
    }

} // valida_cpf_cnpj

/*
formata_cpf_cnpj

Formata um CPF ou CNPJ

@access public
@return string CPF ou CNPJ formatado
*/
function formata_cpf_cnpj(valor) {

    // O valor formatado
    var formatado = false;

    // Verifica se é CPF ou CNPJ
    var valida = verifica_cpf_cnpj(valor);

    // Garante que o valor é uma string
    valor = valor.toString();

    // Remove caracteres inválidos do valor
    valor = valor.replace(/[^0-9]/g, '');


    // Valida CPF
    if (valida === 'CPF') {

        // Verifica se o CPF é válido
        if (valida_cpf(valor)) {

            // Formata o CPF ###.###.###-##
            formatado = valor.substr(0, 3) + '.';
            formatado += valor.substr(3, 3) + '.';
            formatado += valor.substr(6, 3) + '-';
            formatado += valor.substr(9, 2) + '';

            //habilitar
            habilita('nome');
            habilita('rua');
            habilita('complemento');
            habilita('numero');
            habilita('bairro');
            habilita('cep');
            habilita('cidade');
            habilita('estado');
            habilita('telefone_fixo');
            habilita('telefone');
            habilita('email');
            habilita('nome_fant');
        }

    }

    // Valida CNPJ
    else if (valida === 'CNPJ') {

        // Verifica se o CNPJ é válido
        if (valida_cnpj(valor)) {
            // Formata o CNPJ ##.###.###/####-##
            formatado = valor.substr(0, 2) + '.';
            formatado += valor.substr(2, 3) + '.';
            formatado += valor.substr(5, 3) + '/';
            formatado += valor.substr(8, 4) + '-';
            formatado += valor.substr(12, 14) + '';

            //Habilita campos
            habilita('nome');
            habilita('rua');
            habilita('complemento');
            habilita('numero');
            habilita('bairro');
            habilita('cep');
            habilita('cidade');
            habilita('estado');
            habilita('telefone_fixo');
            habilita('telefone');
            habilita('email');
            habilita('nome_fant');
        }

    }
    return formatado;
}
$(function () {

    // ## EXEMPLO 2
    // Aciona a validação ao sair do input
    document.getElementById('cpf_cnpj').onkeypress = function () {
        // O CPF ou CNPJ
        if (event.key === 'Enter') {
            var cpf_cnpj = $(this).val();
            if (cpf_cnpj !== "") {
                if (formata_cpf_cnpj(cpf_cnpj)) {
                    if (cpf_cnpj.length === 14 && cpf_cnpj.indexOf('.') === -1) {
                        $.ajax({
                            'url': 'https://www.receitaws.com.br/v1/cnpj/' + cpf_cnpj.replace(/[^0-9]/g, ''),
                            'type': "GET",
                            'dataType': 'jsonp',
                            'success': function (data) {
                                document.getElementById('nome').value = data.nome;
                                document.getElementById('nome_fant').value = data.fantasia;
                                document.getElementById('rua').value = data.logradouro;
                                document.getElementById('complemento').value = data.complemento;
                                document.getElementById('numero').value = data.numero;
                                document.getElementById('bairro').value = data.bairro;
                                document.getElementById('cep').value = data.cep;
                                document.getElementById('cidade').value = data.municipio;
                                document.getElementById('estado').value = data.uf;
                                document.getElementById('telefone_fixo').value = data.telefone;
                                console.log(data);
                            }
                        })
                    }
                    const div = document.getElementById('verificacaocpf');
                    div.textContent = ""
                    $(this).val(formata_cpf_cnpj(cpf_cnpj));
                } else {
                    const div = document.getElementById('verificacaocpf');
                    div.textContent = "CPF ou CNPJ inválido!"
                }
            } else {
                const div = document.getElementById('verificacaocpf');
                div.textContent = "Informe um CPF/CNPJ"
            }
        }
    };
});
$('#enviar').on('keyup keypress', "input", function (e) {
    var keyCode = e.keyCode || e.which;
    if (keyCode === 13) {
        e.preventDefault();
        return false;
    }
});
function desabilita(i) {

    document.getElementById(i).disabled = true;
}
function habilita(i) {
    document.getElementById(i).disabled = false;
}