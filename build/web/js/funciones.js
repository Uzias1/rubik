/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * https://codepen.io/janio/pen/wGOGRa
 * 
 * https://parzibyte.me/blog/2021/07/19/cronometro-javascript-open-source/
 */

document.addEventListener("DOMContentLoaded", () => {
	const $tiempoTranscurrido = document.querySelector("#tiempoTranscurrido"),
                $btnMezclar = document.querySelector("#mezclar"),
		$btnIniciar = document.querySelector("#btnIniciar"),
		$btnMarca = document.querySelector("#btnMarca"),
		$btnDetener = document.querySelector("#btnDetener"),
		$btnGuardar = document.querySelector("#btnGuardar"),
		$contenedorMarcas = document.querySelector("#contenedorMarcas");
	let marcas = [],
		idInterval,
		tiempoInicio = null;
	let diferenciaTemporal = 0;

	const ocultarElemento = elemento => {
		elemento.setAttribute('disabled', "true");
	}

	const mostrarElemento = elemento => {
		elemento.removeAttribute('disabled')
	}

	const agregarCeroSiEsNecesario = valor => {
		if (valor < 10) {
			return "0" + valor;
		} else {
			return "" + valor;
		}
	}

	const milisegundosAMinutosYSegundos = (milisegundos) => {
		const minutos = parseInt(milisegundos / 1000 / 60);
		milisegundos -= minutos * 60 * 1000;
		segundos = (milisegundos / 1000);
		return `${agregarCeroSiEsNecesario(minutos)}:${agregarCeroSiEsNecesario(segundos.toFixed(1))}`;
	};


	const iniciar = () => {
		const ahora = new Date();
		tiempoInicio = new Date(ahora.getTime() - diferenciaTemporal);
		clearInterval(idInterval);
                //borrar lo que había
                marcas = [];
		dibujarMarcas();
		idInterval = setInterval(refrescarTiempo, 100);
		ocultarElemento($btnIniciar);
		ocultarElemento($btnDetener);
                ocultarElemento($btnMezclar);
		ocultarElemento($btnGuardar);
		mostrarElemento($btnMarca);
	};
        
	const refrescarTiempo = () => {
		const ahora = new Date();
		const diferencia = ahora.getTime() - tiempoInicio.getTime();
		$tiempoTranscurrido.textContent = milisegundosAMinutosYSegundos(diferencia);
	};
	const ponerMarca = () => {
		marcas.unshift(new Date() - tiempoInicio.getTime());
		dibujarMarcas();
                mostrarElemento($btnDetener);
                ocultarElemento($btnMarca);
	};
	const dibujarMarcas = () => {
		$contenedorMarcas.innerHTML = "";
		for (const [indice, marca] of marcas.entries()) {
			const $li = document.createElement("p");
			$li.innerHTML = `<strong class="is-size-4"></strong> ${milisegundosAMinutosYSegundos(marca)}`;
			$li.classList.add("is-size-3");
			$contenedorMarcas.append($li);
		}
	};

	const detener = () => {
		//if (!confirm("¿Detener?")) {
		//	return;
		//}
                
		clearInterval(idInterval);
		//init();
		//marcas = [];
		//dibujarMarcas();
                ocultarElemento($btnMarca);
                ocultarElemento($btnDetener);
                mostrarElemento($btnIniciar);
                mostrarElemento($btnGuardar);
                mostrarElemento($btnMezclar);
                //establecer el valor que había en los inputs
                document.getElementById("c").value = document.getElementById("combinacion").innerHTML;
                document.getElementById("o").value = $contenedorMarcas.textContent;
                document.getElementById("t").value = $tiempoTranscurrido.textContent;
		diferenciaTemporal = 0;
	}

	const init = () => {
		$tiempoTranscurrido.textContent = "00:00.0";
		ocultarElemento($btnMarca);
		ocultarElemento($btnDetener);
	};
	init();

	$btnIniciar.onclick = iniciar;
	$btnMarca.onclick = ponerMarca;
	$btnDetener.onclick = detener;
});
/*
function cambiarComb(){
    var x = document.getElementById("combinacion").innerHTML;
    var y = document.getElementById("idMax").innerHTML;
    var z = document.getElementById("idComb").innerHTML;
    
    var idNuevo = getRandom(1,y);
    if(idNuevo != z){
        document.getElementById("idComb").innerHTML = idNuevo;
    } else {
        cambiarComb();
    }
    
}

function getRandom(min, max) {
  return Math.floor(Math.random()  * (max - min) + min);
}*/
