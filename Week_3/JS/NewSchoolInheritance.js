class Hero{
    constructor(heroName= "N/A", heroLevel = 1){
        this.heroName = heroName;
        this.heroLevel = heroLevel;
    }
    sayHello(){
        console.log(`${this.heroName}, a level ${this.heroLevel} hero, says hello!`);
    }
}

class Warrior extends Hero{
    constructor(heroName="N/A", heroLevel=1, heroWeapon="a wooden stick"){
        super(heroName, heroLevel);
        this.heroWeapon = heroWeapon;
    }
    attack(){
        console.log(`${this.heroName}, a level ${this.heroLevel} warrior, attacks with ${this.heroWeapon} for ${Math.round(Math.random() * 10000)} damage!`);
    }
}

class Wizard extends Hero{
    constructor(heroName = "N/A", heroLevel=1, heroSpell="Ultima"){
        super(heroName, heroLevel);
        this.heroSpell = heroSpell;
    }
    cast(){
        console.log(`${this.heroName}, a level ${this.heroLevel} wizard, casts ${this.heroSpell} for ${Math.round(Math.random() * 10000)} damage!`);
    }
}

let Tidus = new Hero("Tidus", 99);
let Auron = new Warrior("Auron", 455, "Muramsa");
let Lulu = new Wizard("Lulu", 75, "Flare")

Tidus.sayHello();
Auron.sayHello();
Auron.attack();
Lulu.sayHello();
Lulu.cast();
