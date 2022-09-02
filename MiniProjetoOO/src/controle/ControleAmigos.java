package controle;

import java.util.ArrayList;

import modelo.Amigo;
import modelo.Despesa;
import modelo.Usuario;

/**
 * Classe que realiza algumas operações de busca na lista de amigos
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see ControleDados
**/
public class ControleAmigos {
		private ArrayList<Amigo> amigos;
		
		/**
		 * Construtor ControleAmigos
		 * 
		 * @param controleDados ControleDados
		 * **/
		public ControleAmigos(ControleDados controleDados) {
			this.amigos = controleDados.getAmigos();
		}
		
		public ArrayList<Amigo> getAmigos(){
			return amigos;
		}
		
		/**
		 * Método percorre a lista de amigos e verifica se o id do amigo é igual ao fornecido como parâmetro
		 * 
		 * @param id int
		 * @return Amigo com o id igual ao fornecido
		 */
		public Amigo getAmigoPorId(int id) {
			Amigo amigo = amigos.get(0);
			for(int i = 0; i < amigos.size(); i++) {
				if(amigos.get(i).getId() == id) {
					amigo = amigos.get(i);
				}
			}
			return amigo;
		}
		
		/**
		 * Método percorre a lista de amigos e verifica se o email do amigo é igual ao fornecido como parâmetro
		 * 
		 * @param email String
		 * @return Amigo com o email igual ao fornecido
		 */
		public Amigo getAmigoPorEmail(String email) {
			Amigo amigo = amigos.get(0);
			for(int i = 0; i<amigos.size(); i++) {
				if(amigos.get(i).getEmail().equals(email)) {
					amigo = amigos.get(i);
				}
			}
			return amigo;
		}
		
		/**
		 * Método fornece a lista de usuarios dos quais essa instancia de Amigo é amigo
		 * 
		 * @param id int
		 * @return ArrayList(Usuario) com os usuarios que esse amigo possui como amigo
		 */
		public ArrayList<Usuario> getUsuariosAmigo(int id) {
			return getAmigoPorId(id).getAmigosDe();
		}
		
		public ArrayList<Despesa> getDebitosAmigo(int id) {
			return getAmigoPorId(id).getDebitos();
		}
		
		public ArrayList<Despesa> getCreditosUsuario(int id) {
			return getAmigoPorId(id).getCreditos();
		}
		
		/**
		 * Método percorre a lista de amigos e verifica se o email fornecido já pertence à um amigo
		 * 
		 * @param email String
		 * @return true caso já exista um amigo com esse email false caso contrario
		 */
		public boolean amigoExiste(String email) {
			for(int i = 0; i<amigos.size(); i++) {
				if(amigos.get(i).getEmail().equals(email)) {
					return true;
				}
			}
			return false;
		}
}
