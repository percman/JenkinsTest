function outer() {
    let a = 3;
    function inner1() {
        let b = 4;
        function innermostSanctum() {
            let c = 5;
            console.log(a,b,c);
        }
        // console.log(c);
        innermostSanctum();
        //console.log(c);
    }
    // console.log(b);
    inner1();
}
outer();

var Person = function(name, age) {
    //CONSTRUCTOR LOGIC WOOOH
    this.name = name;
    this.age = age;

    // We can create this closure to simulate encapsulation.
    return {
        // Using arrow note to ensure the "this" refers to the Person
        getAge:() => this.age,
        setAge: (age) => this.age = age,
        getName:() => this.name,
        setName: (age) => this.age = name,
        info: () => console.log(`Name: ${this.name}\nAge: ${this.age}`),
        theObject: () => this
    }
}

let William = new Person("William", 25);
console.log(William.getName());
console.log(William);
console.log(William.theObject());