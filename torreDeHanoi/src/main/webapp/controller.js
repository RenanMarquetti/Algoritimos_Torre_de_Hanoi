let canvas = document.getElementById("areaDesenho")
let ctx = canvas.getContext("2d")

let torre

function initTorre() {
    montarTorre()
    iniciar()
}

function montarTorre() {
    torre = new Torre(ctx, 100, 450, 750, 250, 5, document.getElementById("inicio").value)
	let inicio = document.getElementById("inicio").value
	let fim = document.getElementById("fim").value
	fetch('http://localhost:8080/torreDeHanoi/grafo?init='+inicio.replaceAll('|', '-')+"&fim="+fim.replaceAll('|', '-'))
	.then(response => {
		response.text().then(r => {
			torre.desenhar()
			document.getElementById("sequencia").innerHTML = r
		})	
	})
}

function iniciar() {
    torre.calcularSequencia(document.getElementById("sequencia").innerText)
}
