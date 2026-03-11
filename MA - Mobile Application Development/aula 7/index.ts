type Profile = "Empregado" | "Vagabundo" | "Desempregado a procura de emprego"

type User ={
    frisName: string;
    lastName: string;
    email: string;
    profile: Profile
}

let Andre: User = {
    frisName: "Andre",
    lastName: "Colombo",
    email: "andrecolombo@gmail.com",
    profile: "Desempregado a procura de emprego"
}

let Andre2 = {
    frisName: "Andre",
    lastName: "Colombo",
    email: "andrecolombo@gmail.com"
}




interface MyUser {
    frisName: string;
    lastName: string;
    email: string;
}

let Andre3: MyUser = {
    frisName: "Andre",
    lastName: "Colombo",
    email: "andrecolombo@gmail.com"
}




interface feline{
    breed: string;
}
interface cat {
    breed: "cat"
}

type Siames = feline | cat;

const myCat: Siames = {
    breed : "cat"
}
const otherCat: Siames = {
    breed: "lion"
}

console.log(`My cat ${myCat.breed}`)
console.log(`My cat ${otherCat.breed}`)




interface IPerson {
    name: string;
    email: string;
    sayMyName: () => void
    sayHello: (name: string) => boolean
}

class Person implements IPerson{
    name: string;
    email: string;

    constructor(name: string, email: string){
        this.name = name;
        this.email = email;
    }

    sayHello(name: string): boolean {
        return true;
    }

    sayMyName (){
        console.log(`say my name ${this.name}`)
    }
}