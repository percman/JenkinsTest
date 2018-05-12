class Hero {
    constructor(heroName="N/A", heroLevel=1){
        this.heroName = heroName;
        this.heroLevel = heroLevel;
    }
    sayHello() {
        console.log(`${this.heroName}, a level ${this.heroLevel} hero, says hello!`);
    }
}

class Warrior extends Hero {
    constructor(heroName="N/A", heroLevel=1, heroWeapon="a wooden stick") {
        super(heroName, heroLevel);
        this.heroWeapon = heroWeapon;
    }
    attack() {
        console.log(`${this.heroName}, a level ${this.heroLevel} warrior, attacks with ${this.heroWeapon} for ${Math.round(Math.random() * 100)} damage!`);
    }
}

class Wizard extends Hero {
    constructor(heroName="N/A", heroLevel=1, heroSpell) {
        super(heroName, heroLevel);
        this.heroSpell = heroSpell;
    }
    cast() {
        console.log(`${this.heroName}, a level ${this.heroLevel} wizard, attacks with ${this.heroSpell} for ${Math.round(Math.random() * 10000)} damage!`);
    }
}

let titus = new Hero("Titus", 99);
let auron = new Warrior("Auron", 455, "Mursamune");
let lulu = new Wizard("LuLu", 75, "Flare");

titus.sayHello();
// Because auron is a warrior, and warrior extends hero
auron.sayHello();
auron.attack();
// Because lulu is a wizard, and wizard extends hero
lulu.sayHello();
lulu.cast();