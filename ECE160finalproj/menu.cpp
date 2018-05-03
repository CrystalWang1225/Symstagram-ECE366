#include "menu.h"
void menu::drawpause(sf::RenderWindow & window)
{
	window.draw(startbutton_text);
}

void menu::drawscore(sf::RenderWindow & window, player player)
{
	playerscore.setString(std::to_string(player.points));
	window.draw(playerscore);
}

void menu::drawiframe(sf::RenderWindow & window, player player)
{
	playeriframes.setString(std::to_string(player.iframe));
	window.draw(playeriframes);
}

void menu::drawmultiplier(sf::RenderWindow & window, player player)
{
	multiplier.setString("x" + std::to_string(player.multiplier));
	window.draw(multiplier);
}