"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
let Andre = {
    frisName: "Andre",
    lastName: "Colombo",
    email: "andrecolombo@gmail.com",
    profile: "Desempregado a procura de emprego"
};
let Andre2 = {
    frisName: "Andre",
    lastName: "Colombo",
    email: "andrecolombo@gmail.com"
};
let Andre3 = {
    frisName: "Andre",
    lastName: "Colombo",
    email: "andrecolombo@gmail.com"
};
const myCat = {
    breed: "cat"
};
const otherCat = {
    breed: "lion"
};
console.log(`My cat ${myCat.breed}`);
console.log(`My cat ${otherCat.breed}`);
class Person {
    name;
    email;
    constructor(name, email) {
        this.name = name;
        this.email = email;
    }
    sayHello(name) {
        return true;
    }
    sayMyName() {
        console.log(`say my name ${this.name}`);
    }
}
//# sourceMappingURL=index.js.map