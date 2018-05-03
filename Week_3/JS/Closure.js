function outer(){
    let a = 3;
    function inner1(){
        let b = 4;
        function inner2(){
            let c = 5;
            console.log(a+b+c);
        }
        // console.log(c);
        inner2();
    }
    // console.log(b);
    inner1();
}
// outer();

// var name = "Eddy";

var Person = function(name, age){
    //Constructor logic
    this.name = name;
    this.age = age;

    //We can create this closure to simulate encapsulation
    return {
        //We use the arrow function to ensure that the "this" refers to the Person object
        getName: () => {
            return this.name;
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
            console.log(`Name: ${this.name} \nAge: ${this.age}`);
        }
    }
}

// let eddy = new Person("Eddy", 25);
// console.log(eddy.getName());
// console.log(eddy.getAge());
// eddy.setName("Eduardo");
// eddy.setAge(80);
// eddy.info();