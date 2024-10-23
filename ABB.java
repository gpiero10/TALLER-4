package aed;
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int cardinal;

    private class Nodo {
        // Agregar atributos privados del Nodo
        Nodo padre;
        Nodo hijomenor;
        Nodo hijomayor;
        T valor;

        // Crear Constructor del nodo
        Nodo(T v){
            this.valor = v;
            this.padre = null;
            this.hijomenor = null;
            this.hijomayor = null;
        }
    }

    public ABB() {
        this.raiz = null;
        this.cardinal = 0;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){
        Nodo minimo = this.raiz;
        Nodo anterior = null;
        
        while(minimo != null){
            anterior = minimo;
            minimo = minimo.hijomenor;
        }
        return anterior.valor;
    }

    public T maximo(){
        Nodo maximo = this.raiz;
        Nodo anterior = null;
        
        while(maximo != null){
            anterior = maximo;
            maximo = maximo.hijomayor;
        }
        return anterior.valor;
    }

    public void insertar(T elem){
        Nodo actual = this.raiz;
        if (this.cardinal == 0){
            this.raiz = new Nodo(elem);
            this.raiz.padre = null;
            this.raiz.hijomenor = null;
            this.raiz.hijomayor = null;
            this.cardinal += 1;
            return;
        } else {
            if (this.pertenece(elem) == false) {
                Nodo nuevohijo = new Nodo(elem);
                Nodo padre = buscarposiblepadre(elem);
                nuevohijo.padre = padre;  
                
                if(elem.compareTo(padre.valor)<0){
                    padre.hijomenor = nuevohijo;
                } else if(elem.compareTo(padre.valor)>0){
                    padre.hijomayor = nuevohijo;
                }    
                this.cardinal += 1;

            } else {
                return;
            }  
        } 
    }


    private Nodo buscarposiblepadre(T elem){
        Nodo actual = this.raiz;
        Nodo ultimo = null;
        if (this.pertenece(elem)==false){    
            while(actual != null){
                if(elem.compareTo(actual.valor)<0){
                    ultimo = actual;
                    actual = actual.hijomenor;
                } else if(elem.compareTo(actual.valor)>0){
                    ultimo = actual;
                    actual = actual.hijomayor;
                }
            }
        return ultimo;
        }else{
            return null;
        }
    }

    public boolean pertenece(T elem){
        Nodo actual = this.raiz;
        while(actual != null){
            if(elem.compareTo(actual.valor)<0){
                actual = actual.hijomenor;
            } else if(elem.compareTo(actual.valor)>0){
                actual = actual.hijomayor;
            }else {
                return true;
            }
        }
        return false;
    }
    
    
    private Nodo sucesorporizq(Nodo n){
        Nodo mayordelaizq = n.hijomenor;
        Nodo anterior = null;
        while (mayordelaizq != null) { 
            anterior = mayordelaizq;
            mayordelaizq = mayordelaizq.hijomayor;
        }
        return anterior;
    }

    private Nodo sucesorporderecha(Nodo n){
        Nodo menordelader = n.hijomayor;
        Nodo anterior = null;
        while (menordelader != null) { 
            anterior = menordelader;
            menordelader = menordelader.hijomenor;
        }
        return anterior;
    }
    
    public void eliminar(T elem){
        if (pertenece(elem) == false) {
            return;
        } else {
            Nodo eliminado = buscarnodo(elem);
            Nodo sucesoriz = sucesorporizq(eliminado);
            Nodo sucesorder = sucesorporderecha(eliminado);
            
            if (cuantoshijos(eliminado) == 2) {
                if (eliminado == raiz) {
                    reemplazo(raiz, sucesorder);
                    cardinal -= 1;                    
                }else{
                    reemplazo(eliminado,sucesorder);
                    cardinal -=1 ;}

            } else if(cuantoshijos(eliminado) == 1){
                if (eliminado == raiz) {
                    if(eliminado.hijomenor!=null){
                        reemplazo(raiz, sucesoriz);
                        cardinal -=1 ;
                    }else if(eliminado.hijomayor!=null){
                        reemplazo(raiz, sucesorder);
                        cardinal -=1 ;
                    }
                }else{
                    if(eliminado.hijomenor!=null){
                        reemplazo(eliminado, sucesoriz);
                        cardinal -=1 ;
                    }else if(eliminado.hijomayor!=null){
                        reemplazo(eliminado, sucesorder);
                        cardinal -=1 ;
                    }
                }
            } else {
                if(eliminado == raiz){
                   this.raiz = null;
                   this.cardinal = 0; 
                }else{
                    if (eliminado.valor.compareTo(eliminado.padre.valor)<0){
                        eliminado.padre.hijomenor = null;
                    }else if (eliminado.valor.compareTo(eliminado.padre.valor)>0){
                        eliminado.padre.hijomayor = null;
                    } 
                    this.cardinal -=1;
                }
            }
            
                
        }

    }
    

    private void reemplazo(Nodo e, Nodo r){
        if (r.valor.compareTo(e.valor)>0){
            e.valor = r.valor;
            if(r.hijomayor != null){    
                r.hijomayor.padre = r.padre;}
            if(r == r.padre.hijomenor){
                r.padre.hijomenor = r.hijomayor;}
            else if (r == r.padre.hijomayor){
                r.padre.hijomayor = r.hijomayor;
            }

        }else if (r.valor.compareTo(e.valor)<0) {
            e.valor = r.valor;
            if(r.hijomenor != null){r.hijomenor.padre = r.padre;}
            if(r == r.padre.hijomenor){
                r.padre.hijomenor = r.hijomenor;}
            else if (r == r.padre.hijomayor){
                r.padre.hijomayor = r.hijomenor;
            }
        }   
    } 



    private int cuantoshijos(Nodo n){
        int res = 0;
        if (n.hijomayor != null && n.hijomenor != null){
            res = 2;
            return res;
        }
        else if (n.hijomayor == null && n.hijomenor != null || n.hijomayor != null && n.hijomenor == null) {
            res = 1;
            return res;  
        }
        else {
            return 0;
        }
    }

    private Nodo buscarnodo(T elem){
            Nodo actual = this.raiz;
            if (this.pertenece(elem)==true){
                while (actual.valor != elem) { 
                    if (elem.compareTo(actual.valor) > 0){
                        actual = actual.hijomayor;
                    } else if (elem.compareTo(actual.valor) < 0){
                        actual = actual.hijomenor;
                    }
                }
                return actual;
            }else{
                return null;
            }
    }


    public String toString(){
        String res = "{";
        if (this.cardinal == 0){
            return "{}";
        }else{
            Iterador<T> it = this.iterador();
            Nodo inicio = buscarnodo(it.siguiente());
            while(inicio != buscarnodo(maximo())){
                res += inicio.valor+",";
                inicio = buscarnodo(it.siguiente());
            }       
            res += maximo()+"}";
            return res;
        }
    }

   

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private Nodo minimo;
        private Nodo max;

        public ABB_Iterador(){
            this._actual = null;
            this.minimo = minimo(raiz);
            this.max = maximo(raiz);
        } 

        private Nodo maximo(Nodo n){
            Nodo anterior = null;
            
            while(n != null){
                anterior = n;
                n = n.hijomayor;
            }
            return anterior;
        }

        private Nodo minimo(Nodo n){
            Nodo anterior = null;
            while(n != null){
                anterior = n;
                n = n.hijomenor;
            }
            return anterior;
        }
        
        private Nodo sucesorporderecha(Nodo n){
            Nodo menordelader = n.hijomayor;
            Nodo anterior = null;
            while (menordelader != null) { 
                anterior = menordelader;
                menordelader = menordelader.hijomenor;
            }
            return anterior;
        }

        private Nodo sucesorporarriba(Nodo n){
            Nodo arriba = n.padre;
            while(arriba != null){
                if(arriba.valor.compareTo(n.valor)>0){
                    return arriba;
                } else {
                    arriba = arriba.padre;
                }
            }
            return null;
        }

        public boolean haySiguiente() {            
            if (siguientenodo()!=null){
                return true;
            } else{
                return false;
            }
        }
    
        public T siguiente() {
            return siguientenodo().valor;
        }

        private Nodo siguientenodo() {
            if (this._actual == null){
                this._actual = minimo;
                return _actual;
            }else if(this._actual == max){
                return null;
            } else {
                if(_actual.hijomayor != null){
                    this._actual = sucesorporderecha(_actual);
                    return this._actual;
                } else {
                    this._actual = sucesorporarriba(_actual);
                    return this._actual;
                }
            }
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
