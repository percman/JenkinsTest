class Hero {
    constructor(heroName = "N/A", heroLevel=1){
        this.heroName = heroName;
        this.heroLevel = heroLevel;
    }

    sayHello(){
        console.log(`${this.heroName}, a level ${this.heroLevel} hero, says hello!`);
    }
}

class Shepard extends Hero {
    constructor(heroName="N/A", heroLevel=1, heroWeapon="a sword"){
        super(heroName, heroLevel);
        this.heroWeapon = heroWeapon;
    }
    attack() {
        console.log(`${this.heroName}, a level ${this.heroLevel} Shepard, 
            attacks with ${this.heroWeapon} for ${Math.round(Math.random() * 100)} damage!`);
    }
}

class Seraph extends Hero {
    constructor(heroName="N/A", heroLevel=1, heroSpell="Aselia"){
        super(heroName, heroLevel);
        this.heroSpell = heroSpell;
    }
    cast() {
        console.log(`${this.heroName}, a level ${this.heroLevel} Seraph, 
            attacks with ${this.heroSpell} for ${Math.round(Math.random() * 100)} damage!`);
    }

}

let sorey = new Shepard("Sorey", 75, "the Shepard's Sword");
let mikleo = new Seraph("Mikleo", 74,);
let rose = new Hero("Rose", 63);

rose.sayHello();

sorey.sayHello();
sorey.attack();

mikleo.sayHello();
mikleo.cast();