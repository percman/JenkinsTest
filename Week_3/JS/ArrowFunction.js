//Why do we even need the arrow function?
//It does not create its own "this" value, it takes the "this"
//From its enclosing context

function Person(){
    this.age = 0;
    //Self envoking function to illustrate how it was before ES6

    (function(){
        for(let i = 0; i < 50; i++){
            this.age++;
            console.log(this.age);
        }

    })();

}

//let p = new Person();

function BetterPerson(){
    this.age = 0;

    (() => {
        for(let i = 0; i < 50; i++){
            this.age++;
            console.log(this.age);
        }
    })();
}

//let bp = new BetterPerson();

let add = (a,b) => a + b;
let greet = name => {
    alert("Hello, " + name + "!");
}


// console.log(add(10,20));
// greet("Eddy");

