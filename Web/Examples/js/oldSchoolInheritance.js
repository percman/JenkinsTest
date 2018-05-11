function Hero(heroName="Cloud", heroLevel=1) {
    this.heroName = heroName;
    this.heroLevel = heroLevel;
}

Hero.prototype.sayHello = function () {
    console.log(`${this.heroName}, a level ${this.heroLevel} hero, says hello!`)
}

let hero = new Hero("Tidus", 99);
hero.sayHello();

function Warrior(heroName = "Cloud", heroLevel=99, heroWeapon="the Brotherhood") {
    Hero.call(this, heroName, heroLevel);
    this.heroWeapon = heroWeapon;
}

// Warrior.prototype = Object.create(Hero.prototype);
Warrior.prototype = Object.create(Hero.prototype);
Warrior.prototype.attack = function () {
    console.log(`${this.heroName} attacks!`);
}
let warrior = new Warrior("Sora", 99, "Oathkeeper");
warrior.sayHello();
warrior.attack();
console.log(warrior);

function Wizard(heroName="LuLu", heroLevel=45, heroSpell="Ultima") {
    Hero.call(this, heroName, heroLevel);
    this.heroSpell = heroSpell;
}

Wizard.prototype = Object.create(Hero.prototype);
Wizard.prototype.cast = function() {
    console.log(`${this.heroName} protecs`);
}

let wiz = new Wizard();
wiz.sayHello();
wiz.cast();

function Spellsword(heroName="Noctis", heroLevel=72, heroWeapon="Ultima Blade", heroSpell="Firaga") {
    Wizard.call(this, heroName, heroLevel, heroSpell);
    Warrior.call(this, heroName, heroLevel, heroWeapon);
}

Spellsword.prototype = Object.create(Wizard.prototype);
console.log(a);
Spellsword.prototype = Object.create(Warrior.prototype);

var a = 3;