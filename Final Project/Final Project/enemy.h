#pragma once
#include <SFML/Graphics.hpp>
#include <math.h>
#include "player.h"
#include <iostream>
//GENERIC ENEMY
class enemy {
private:
	sf::Texture etexture;
	float enemy_dx;
	float en_width, en_height;
	bool pointcounted;

public:
	sf::RectangleShape enemybox;
	enemy(float speed);
	void enemymove();
	void enemycheckbounds();
	void resetenemypos();
	bool enemyhit(player player);
	void drawenemy(sf::RenderWindow &window);
	void enemypoint(player &player);
	void print() { std::cout << "kys" << std::endl; }
};