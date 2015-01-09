#include <glut.h>;
void init(void)
{
//Выбрать фоновый (очищающий) цвет
glClearColor(1.0, 1.0, 1.0, 0.0);
//Установить проекцию
glMatrixMode(GL_PROJECTION);
glLoadIdentity();
glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
}
void display(void)
{
//Очистить экран glClear(GL_COLOR_BUFFER_BIT);
 glClear(GL_COLOR_BUFFER_BIT);
//Нарисовать белый полигон (квадрат) с углами //в (0.25, 0.25, 0.0) и (0.75,
//0.75, 0.0)
glColor3f(1.0, 0.0, 1.0);
glBegin(GL_POLYGON);
glVertex3f(0.2, 1.0, 0.0);
glVertex3f(0.4, 1.0, 0.0);
glVertex3f(0.4, 0.8, 0.0);
glVertex3f(0.2, 0.8, 0.0);
glEnd();
//Не ждем. Начинаем выполнять буферизованные
//команды OpenGL
glFlush();
}
//Установить начальные характеристики окна,
//открыть окно с заголовком «4 по 4».
//Зарегистрировать дисплейную функцию обратного вызова
//Войти в главный цикл
int main(int argc, char **argv)
{
glutInit(&argc,argv);
glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
glutInitWindowSize(250, 250);
glutInitWindowPosition(200, 150);
glutCreateWindow("4 по 4");
init();
glutDisplayFunc(display);
glutMainLoop();
return 0;
}