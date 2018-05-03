#pragma once
#include <SFML\Graphics.hpp>
#include "player.h"
class player;

class health {
public:
	sf::RectangleShape healthbar;
	float percenthp;
	float fullbarsize;
public:
	health() {
		fullbarsize = 200.0f;
		healthbar.setSize(sf::Vector2f(25.0f, fullbarsize));
		healthbar.setOrigin(25.0f / 2, fullbarsize / 2);
		healthbar.setFillColor(sf::Color::Red);
		healthbar.setPosition(25.0f, 256.0f);
		percenthp = 1.0f;
	}
	void updatehp(player player);
	void drawhealth(sf::RenderWindow &window);
};