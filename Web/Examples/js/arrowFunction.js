// Why do we even need the arrow function?
// It doesn't create its own "this" value, it takes the "this" from its enclosing context.

function Person() {
    this.age = 0;
    
    //Self-invoking func to illustrate how it was before ES6
    (function() {
        for (let i = 0; i < 50; i++) {
            this.age++;
            console.log(this.age);
        }
    }());
    console.log(this.age);
}

// let p = new Person();

function Person2() {
    this.age = 0;
    
    //Self-invoking func to illustrate how it was before ES6
    (()=> {
        for (let i = 0; i < 50; i++) {
            this.age++;
            console.log(this.age);
        }
    })();
    console.log(this.age);
}
// let bp = new Person2();

let add = (a, b) => a + b;
console.log(add(10,20));
console.log(10,20);