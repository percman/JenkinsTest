class Hero {
    constructor(heroName="N/A", heroLevel=1) {
        this.heroName = heroName;
        this.heroLevel = heroLevel;
    }
    sayHello() {
        console.log(`${this.heroName} says hello!`);
    }
}

class Warrior extends Hero {
    constructor(heroName="N/A", heroLevel=1, heroWeapon="a wooden stick") {
        super(heroName, heroLevel);
        this.heroWeapon = heroWeapon;
    }
    attack() {
        console.log(`${this.heroName} attacs with ${this.heroWeapon}`);
    }
}

class Wizard extends Hero {
    constructor(heroName="N/A", heroLevel=1, heroSpell="a wooden mage hand") {
        super(heroName, heroLevel);
        this.heroSpell = heroSpell;
    }
    cast() {
        console.log(`${this.heroName} protecs with ${this.heroSpell}`);
    }
}

let titus = new Hero("Titus", 99);
let auron = new Warrior("Auron", 455, "Mursamune");
let lulu = new Wizard("Lulu", 75, "Flare");

titus.sayHello();
auron.sayHello();
auron.attack();
lulu.sayHello();
lulu.cast();