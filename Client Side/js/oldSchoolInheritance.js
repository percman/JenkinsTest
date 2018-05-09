function Hero(heroName="Cloud", heroLevel=99) {
    this.heroName = heroName;
    this.heroLevel = heroLevel;
}

Hero.prototype.sayHello = function() {
    console.log(`${this.heroName}, a level ${this.heroLevel} hero, says hello!`);
}

let hero = new Hero();
// hero.sayHello();

hero.nobodyElseCanUseThis = function() {
    console.log(`${this.heroName} says 'Im the only one that can use this'`);
}

// hero.nobodyElseCanUseThis();
// otherHero can use 'nobodyElseCanUseThis' since it is on the hero object only, not the 
// prototype
// let otherHero = new Hero("Barrett", 65);
// otherHero.nobodyElseCanUseThis();

function Warrior(heroName="Cloud", heroLevel=99, heroWeapon="the Brotherhood") {
    // In order to effectively call 'super' in JS, we use 
    Hero.call(this, heroName, heroLevel);
    this.heroWeapon = heroWeapon;
}

// Use this to link an object with another Object's prototype
// To make the Warrior object effectively be a subclass of Hero, we use 
Warrior.prototype = Object.create(Hero.prototype);
Warrior.prototype.attack = function() {
    console.log(`${this.heroName}, a level ${this.heroLevel} warrior, attacks with ${this.heroWeapon} for ${Math.round(Math.random() * 100)} damage!`);
}

let warrior = new Warrior("Sora", 99, "Oathkeeper");
warrior.sayHello();
warrior.attack();
// console.log(warrior);


function Wizard(heroName="LuLu", heroLevel=45, heroSpell="Ultima") {
    Hero.call(this, heroName, heroLevel);
    this.heroSpell = heroSpell;
}

// DONT FORGET TO LINK THE OBJECTS TOGETHER
Wizard.prototype = Object.create(Hero.prototype);
Wizard.prototype.cast = function() {
    console.log(`${this.heroName}, a level ${this.heroLevel} wizard, attacks with ${this.heroSpell} for ${Math.round(Math.random() * 10000)} damage!`);
}

let wiz = new Wizard();
wiz.sayHello();
wiz.cast();

function Spellsword(heroName="Noctis", heroLevel=72, 
    heroWeapon="Ultima Blade", heroSpell="Firaga") {
    Wizard.call(this, heroName, heroLevel, heroSpell);
    Warrior.call(this, heroName, heroLevel, heroWeapon);
}

Spellsword.prototype = Object.create(Wizard.prototype);
Spellsword.prototype = Object.create(Warrior.prototype);

var spell = new Spellsword();
spell.attack();
// spell.cast();