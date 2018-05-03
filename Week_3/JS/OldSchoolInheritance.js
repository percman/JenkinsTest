function Hero(heroName="Cloud", heroLevel=99){
    this.heroName = heroName;
    this.heroLevel = heroLevel;
}
Hero.prototype.sayHello = function(){
    console.log(`${this.heroName}, a level ${this.heroLevel} hero, says hello!`);
}

let hero = new Hero("Tidus", 99);
// hero.sayHello();

hero.nobadyElseCanUseThis = function(){
    console.log(`${this.heroName} says 'I am the only one who can use this.'`);
}

// hero.nobadyElseCanUseThis();
// otherHero can't use nobodyElseCanUseThis since it is on the hero object only, not the prototype
// let otherHero = new Hero("Barrett", 65);
// otherHero.nobadyElseCanUseThis();

function Warrior(heroName = "Cloud", heroLevel = 99, heroWeapon="the Brotherhood"){
    //In order to effectively call 'super' in JS, we use
    Hero.call(this, heroName, heroLevel);
    this.heroWeapon = heroWeapon;
}

// Use this to link an object with ahother Object's prototype
// To make the Warrior oject effectively be a subclass of Hero, we use
Warrior.prototype = Object.create(Hero.prototype);
Warrior.prototype.attack = function(){
    console.log(`${this.heroName}, a level ${this.heroLevel} warrior, attacks with ${this.heroWeapon} for ${Math.round(Math.random() * 100)} damage!`);
}

let warrior = new Warrior("Sora", 99, "Oathkeeper");
warrior.sayHello();
warrior.attack();
console.log(warrior);

function Wizard(heroName="Lulu", heroLevel=45, heroSpell="Ultima"){
    Hero.call(this, heroName, heroLevel);
    this.heroSpell = heroSpell;
}

Wizard.prototype = Object.create(Hero.prototype);
Wizard.prototype.cast = function(){
    console.log(`${this.heroName}, a level ${this.heroLevel} wizard, casts ${this.heroSpell} for ${Math.round(Math.random() * 100)} damage!`);
}

let wizard = new Wizard();
wizard.sayHello();
wizard.cast();

function Spellsword(heroName = "Noctis", heroLevel=72, heroWeapon="Ultima Blade", heroSpell = "Firaga"){
    Wizard.call(this, heroName, heroLevel, heroSpell);
    Warrior.call(this, heroName, heroLevel, heroWeapon);
}

// Spellsword.prototype = Object.create(Wizard.prototype);
Spellsword.prototype = Object.create(Warrior.prototype);

let spell = new Spellsword();
spell.attack();
// spell.cast();

