#include "Health.h"
#include <SFML\Graphics.hpp>
#include <iostream>

void health::updatehp(player player)
{
	percenthp = player.health / player.maxhealth;
	//std::cout << percenthp << std::endl;
	healthbar.setSize(sf::Vector2f(healthbar.getSize().x, fullbarsize * percenthp));
}

void health::drawhealth(sf::RenderWindow & window)
{
	window.draw(healthbar);
}

