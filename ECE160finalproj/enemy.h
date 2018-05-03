#pragma once
#include <SFML/Graphics.hpp>
#include <math.h>
#include "player.h"
#include <iostream>
//GENERIC ENEMY
class enemy {
private:
	float enemy_dx;
	float en_width, en_height;
	bool pointcounted;

public:
	sf::RectangleShape enemybox;
	enemy() {
		enemy_dx = -15.0f;
		en_width = 50.0f;
		en_height = 50.0f;
		enemybox.setSize(sf::Vector2f(en_width, en_height));
		enemybox.setOrigin(en_width / 2, en_height / 2);
		enemybox.setFillColor(sf::Color::Blue);
		float random = (std::rand() % 50) * (512.0f / 51.0f);
		enemybox.setPosition(1050.0f, 252.0f);
		pointcounted = false;
	}
	enemy(float speed);
	void enemymove();
	void enemycheckbounds();
	void resetenemypos();
	bool enemyhit(player player);
	void drawenemy(sf::RenderWindow &window);
	void enemypoint(player &player);
	void print() { std::cout << "kys" << std::endl; }
};