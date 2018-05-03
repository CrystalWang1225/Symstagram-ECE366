#pragma once
#include <SFML\Graphics.hpp>

class ground {
public:
	sf::RectangleShape groundbox;
	float groundwidth, groundheight;
public:
	ground() {
		groundwidth = 1024.0f;
		groundheight = 120.0f;
		groundbox.setSize(sf::Vector2f(groundwidth, groundheight));
		groundbox.setOrigin(groundwidth / 2, groundheight / 2);
		groundbox.setFillColor(sf::Color::Black);
		groundbox.setPosition(512.0f, 512.0f);
	}
	void drawground(sf::RenderWindow &window);
};