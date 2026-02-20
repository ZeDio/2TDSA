console.log("Hello word!")

const nome = "José"
const sobrenome = "Diogo"

console.log(nome +" "+ sobrenome)
console.log(`${nome} ${sobrenome}`.trim())

function sayMyName(nome,sobrenome){
    console.log("Hello word! \nbem vindo " + nome + " " + sobrenome)
}
sayMyName("sla", sobrenome)

const sayMyName1 = (name1) => console.log(`Hello, ${name1}`)
sayMyName1("Andre")

const somar = (a,b) => a+b
const somar2 = (a,b) => {
    return a + b 
}

const resultado = somar(10,10)
const resultado2 = somar2(10,10)

console.log("Resultado", resultado)
console.log("Resultado 2", resultado2)

const meuArray = [1, 2, 3]
console.log("Meu array", meuArray)
console.log("Meu array", ...meuArray)
console.log("Meu array", [meuArray, 4, 5, 6])
console.log("Meu array", [...meuArray, 4, 5, 6])
console.log("Meu array", [4, ...meuArray])

const [first, ...rest] = meuArray;
console.log(first)
console.log(rest)

const user = {
    fristName: "Andre",
    lastName: "Colombo"
}
console.log("User", user)
const secondUser = {user, email: "andre@gmail.com"}
console.log("User", secondUser)
const tresUser = {...user, email: "andre@gmail.com"}
console.log("User", tresUser)

const {fristName} = user;
console.log(fristName)