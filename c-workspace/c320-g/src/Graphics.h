
#pragma once

#include <SDL.h>
#include <string>
#include <map>

using namespace std;

struct SDL_Window;
struct SDL_Renderer;

class Graphics {
public:
	Graphics();

	void putImage(const string &name, const string& path);

	SDL_Surface* loadImage(const string &name);

	SDL_Texture* surfaceToTexture(SDL_Surface* &surface);

	void drawSurface(SDL_Texture* source, SDL_Rect* sourceRectangle, SDL_Rect* destinationRectangle);

	void flip();

	void clear();

	SDL_Renderer* getRenderer() const;
private:
	SDL_Window * window;
	SDL_Renderer* renderer;
	map<string, SDL_Surface*> sprites;


};
