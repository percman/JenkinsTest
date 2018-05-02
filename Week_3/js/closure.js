
function outer(){
    let a = 3;
    function inner(){
        let b = 4;
        function evenMoreInner(){
            let c = 5;
            console.log(a + b + c);
        }
        evenMoreInner();
        // console.log(c);

    }
    inner();
    // console.log(b);

}
// outer();


var Person = function(name, age){
    // Constructor logic
    this.name = name;
    this.age = age;

    // We can create this closure to simulate encapsulation
    return {
        // we use the arrow function to ensure that the "this"
        // refers to the Person object
        getName: () => {
            return this.name
        },
        setName: (name) => {
            this.name = name;
        },
        getAge: () => {
            return this.age;
        },
        setAge: (age) => {
            this.age = age;
        },
        info: () => {
            console.log(`Name: ${this.name}\nAge: ${this.age}`);
        }
    }
}

let Mickey = new Person("Mickey Mouse", 88);
console.log(Mickey.getName());
console.log(Mickey.getAge());
Mickey.setName("Minnie");
Mickey.info();

